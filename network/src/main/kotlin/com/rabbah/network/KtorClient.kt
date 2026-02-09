package com.rabbah.network

import com.rabbah.core.preferences.PreferenceKeys
import com.rabbah.core.preferences.SharedPrefsManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

class KtorClient(
    private val baseUrl: String,
    private val enableLogging: Boolean = false,
    private val sharedPrefsManager: SharedPrefsManager
) {

    internal companion object {
        internal const val USE_MOCK_CLIENT = false
    }

    val client: HttpClient = getKtorClient()

    private fun getKtorClient(): HttpClient {
        return if (USE_MOCK_CLIENT) {
            HttpClient(MockEngine) {
                // Add logging to the mock engine to debug requests and responses
                install(Logging) {
                    level = LogLevel.ALL
                    logger = object : Logger {
                        override fun log(message: String) {
                            println("MockApiClient: $message")
                        }
                    }
                }

                engine {
                    addHandler { request ->
                        // Artificial delay to simulate network latency
                        delay(500)
                        val responseHeaders = headersOf(HttpHeaders.ContentType to listOf("application/json"))
                        when (request.url.encodedPath) {
                            "/homeOffer" -> respond(MockApiUtils.brochures(), HttpStatusCode.OK, responseHeaders)
                            else -> error("Unhandled ${request.url.encodedPath}")
                        }
                    }
                }
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
            }
        } else {
            HttpClient(CIO) {
                defaultRequest {                    url(baseUrl)
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                    sharedPrefsManager.getStringValue(PreferenceKeys.KEY_ACCESS_TOKEN, "")?.let {
                        header(HttpHeaders.Authorization, "Bearer $it")
                    }
                    header(HttpHeaders.AcceptLanguage, sharedPrefsManager.getStringValue(PreferenceKeys.KEY_LANGUAGE, "en"))
                    header("Platform", "Android")

                }

                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }

                if (enableLogging) {
                    install(Logging) {
                        level = LogLevel.ALL
                        logger = object : Logger {
                            override fun log(message: String) {
                                 val modifiedMessage = message
//                                     .replaceFirst("\nBODY START\n","")
//                                     .replaceFirst("\nBODY END","")
//                                     .replaceFirst("METHOD: ","")
                                     .replaceFirst("BODY Content-Type: application/json; charset=utf-8","")
                                     .replaceFirst("BODY Content-Type: application/json","")

                                println("$modifiedMessage\n\n")
                            }
                        }
                    }
                }

                install(HttpTimeout) {
                    requestTimeoutMillis = 15000L
                    connectTimeoutMillis = 15000L
                    socketTimeoutMillis = 15000L
                }
            }
        }
    }
}

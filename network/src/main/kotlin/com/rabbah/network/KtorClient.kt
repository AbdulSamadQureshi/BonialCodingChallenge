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
                            "/appVersion" -> respond(MockApiUtils.appVersion(), HttpStatusCode.OK, responseHeaders)
                            "/login" -> respond(MockApiUtils.login(""), HttpStatusCode.OK, responseHeaders)
                            "/requestOtp" -> respond(MockApiUtils.requestOtp(""), HttpStatusCode.OK, responseHeaders)
                            "/verifyOtp" -> respond(MockApiUtils.verifyOtp(0, ""), HttpStatusCode.OK, responseHeaders)
                            "/homeOffer" -> respond(MockApiUtils.homeOffer(0), HttpStatusCode.OK, responseHeaders)
                            "/updateAccountDetails" -> respond(MockApiUtils.updateAccountDetails("", "", "", ""), HttpStatusCode.OK, responseHeaders)
                            "/accountDetails" -> respond(MockApiUtils.accountDetail(0), HttpStatusCode.OK, responseHeaders)
                            "/logout" -> respond(MockApiUtils.logout(0), HttpStatusCode.OK, responseHeaders)
                            "/updateProfilePicture" -> {
                                val userId = request.url.parameters["userId"]?.toIntOrNull() ?: 0
                                val profilePictureUrl = request.url.parameters["profilePictureUrl"] ?: ""
                                respond(MockApiUtils.updateProfilePicture(userId, profilePictureUrl), HttpStatusCode.OK, responseHeaders)
                            }
                            "/activeOffers" -> {
                                val userId = request.url.parameters["userId"]?.toIntOrNull() ?: 0
                                val pageNumber = request.url.parameters["pageNumber"]?.toIntOrNull() ?: 1
                                respond(MockApiUtils.activeOffers(userId), HttpStatusCode.OK, responseHeaders)
                            }
                            "/expiredOffers" -> {
                                val userId = request.url.parameters["userId"]?.toIntOrNull() ?: 0
                                val pageNumber = request.url.parameters["pageNumber"]?.toIntOrNull() ?: 1
                                respond(MockApiUtils.expiredOffers(userId), HttpStatusCode.OK, responseHeaders)
                            }
                            "/transactionsHistory" -> {
                                val userId = request.url.parameters["userId"]?.toIntOrNull() ?: 0
                                val page = request.url.parameters["page"]?.toIntOrNull() ?: 0
                                respond(MockApiUtils.transactionsHistory(userId, page), HttpStatusCode.OK, responseHeaders)
                            }
                            "/transactionDetails" -> respond(MockApiUtils.transactionDetails(0, 0), HttpStatusCode.OK, responseHeaders)
                            "/nearbyVends" -> {
                                val query = request.url.parameters["query"] ?: ""
                                val lat = request.url.parameters["lat"]?.toDoubleOrNull() ?: 0.0
                                val lon = request.url.parameters["lon"]?.toDoubleOrNull() ?: 0.0
                                val page = request.url.parameters["page"]?.toIntOrNull() ?: 0
                                respond(MockApiUtils.nearbyVends(query, lat, lon, page), HttpStatusCode.OK, responseHeaders)
                            }
                            "/wallet" -> respond(MockApiUtils.wallet(0), HttpStatusCode.OK, responseHeaders)
                            "/lockCard" -> respond(MockApiUtils.lockCard(0), HttpStatusCode.OK, responseHeaders)
                            "/unlockCard" -> respond(MockApiUtils.unlockCard(0), HttpStatusCode.OK, responseHeaders)
                            "/activateCard" -> respond(MockApiUtils.activateCard(0), HttpStatusCode.OK, responseHeaders)
                            "/requestCard" -> respond(MockApiUtils.requestCard(0), HttpStatusCode.OK, responseHeaders)
                            "/topUp" -> respond(MockApiUtils.topUpResponse(0), HttpStatusCode.OK, responseHeaders)
//                            "/vendDetails" -> respond(MockApiUtils.topUpResponse(0), HttpStatusCode.OK, responseHeaders)
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

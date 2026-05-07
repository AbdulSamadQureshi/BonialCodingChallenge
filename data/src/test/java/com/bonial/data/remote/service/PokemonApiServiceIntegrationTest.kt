package com.bonial.data.remote.service

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Verifies that PokemonApiService sends the correct HTTP requests
 * and deserialises well-formed responses from the PokéAPI contract.
 */
class PokemonApiServiceIntegrationTest {
    private val mockWebServer = MockWebServer()
    private lateinit var service: PokemonApiService

    @Before
    fun setUp() {
        mockWebServer.start()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(PokemonApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `pokemon list request hits the correct endpoint path`() =
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody("""{"count":1302,"next":null,"previous":null,"results":[]}"""),
            )

            service.pokemonList(limit = 20, offset = 0)

            val request = mockWebServer.takeRequest()
            assertThat(request.path).startsWith("/pokemon")
        }

    @Test
    fun `pokemon list request includes limit and offset as query parameters`() =
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody("""{"count":1302,"next":null,"previous":null,"results":[]}"""),
            )

            service.pokemonList(limit = 20, offset = 40)

            val request = mockWebServer.takeRequest()
            assertThat(request.path).contains("limit=20")
            assertThat(request.path).contains("offset=40")
        }

    @Test
    fun `pokemon list response is deserialised into the correct total count`() =
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        """
                        {
                          "count": 1302,
                          "next": "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
                          "previous": null,
                          "results": [
                            {"name":"bulbasaur","url":"https://pokeapi.co/api/v2/pokemon/1/"},
                            {"name":"ivysaur","url":"https://pokeapi.co/api/v2/pokemon/2/"}
                          ]
                        }
                        """.trimIndent(),
                    ),
            )

            val response = service.pokemonList(limit = 20, offset = 0)

            assertThat(response.count).isEqualTo(1302)
            assertThat(response.next).isNotNull()
            assertThat(response.results).hasSize(2)
        }

    @Test
    fun `pokemon list response maps each result name and url correctly`() =
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        """
                        {
                          "count": 2,
                          "next": null,
                          "previous": null,
                          "results": [
                            {"name":"bulbasaur","url":"https://pokeapi.co/api/v2/pokemon/1/"},
                            {"name":"charmander","url":"https://pokeapi.co/api/v2/pokemon/4/"}
                          ]
                        }
                        """.trimIndent(),
                    ),
            )

            val response = service.pokemonList(limit = 20, offset = 0)

            assertThat(response.results?.map { it.name }).containsExactly("bulbasaur", "charmander")
            assertThat(response.results?.map { it.url }).containsExactly(
                "https://pokeapi.co/api/v2/pokemon/1/",
                "https://pokeapi.co/api/v2/pokemon/4/",
            )
        }

    @Test
    fun `pokemon list response with null next field reflects no further pages`() =
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody("""{"count":1,"next":null,"previous":null,"results":[{"name":"mew","url":"https://pokeapi.co/api/v2/pokemon/151/"}]}"""),
            )

            val response = service.pokemonList(limit = 20, offset = 0)

            assertThat(response.next).isNull()
        }
}

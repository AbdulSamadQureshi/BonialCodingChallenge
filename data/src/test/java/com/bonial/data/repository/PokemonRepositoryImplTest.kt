package com.bonial.data.repository

import app.cash.turbine.test
import com.bonial.data.remote.model.PokemonItemDto
import com.bonial.data.remote.model.PokemonListResponseDto
import com.bonial.data.remote.service.PokemonApiService
import com.bonial.domain.model.network.response.Request
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class PokemonRepositoryImplTest {
    private val apiService: PokemonApiService = mock()
    private lateinit var repository: PokemonRepositoryImpl

    @Before
    fun setUp() {
        repository = PokemonRepositoryImpl(apiService)
    }

    @Test
    fun `successful response is mapped to a domain PokemonPage with correct pokemon`() =
        runBlocking {
            val response = PokemonListResponseDto(
                count = 1302,
                next = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
                previous = null,
                results = listOf(
                    PokemonItemDto("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
                    PokemonItemDto("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
                ),
            )
            whenever(apiService.pokemonList(limit = 20, offset = 0)).thenReturn(response)

            repository.pokemonList(offset = 0, limit = 20).test {
                assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
                val success = awaitItem() as Request.Success
                assertThat(success.data.totalCount).isEqualTo(1302)
                assertThat(success.data.hasNextPage).isTrue()
                assertThat(success.data.pokemon.map { it.name }).containsExactly("Bulbasaur", "Ivysaur")
                awaitComplete()
            }
        }

    @Test
    fun `offset and limit query parameters are forwarded to the api service`() =
        runBlocking {
            val response = PokemonListResponseDto(
                count = 1302,
                next = null,
                previous = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
                results = listOf(PokemonItemDto("spearow", "https://pokeapi.co/api/v2/pokemon/21/")),
            )
            whenever(apiService.pokemonList(limit = 20, offset = 40)).thenReturn(response)

            repository.pokemonList(offset = 40, limit = 20).test {
                assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
                val success = awaitItem() as Request.Success
                assertThat(success.data.pokemon.single().name).isEqualTo("Spearow")
                awaitComplete()
            }
        }

    @Test
    fun `loading state is emitted before the api response arrives`() =
        runBlocking {
            whenever(apiService.pokemonList(limit = 20, offset = 0)).thenReturn(
                PokemonListResponseDto(count = 0, next = null, previous = null, results = emptyList()),
            )

            repository.pokemonList(offset = 0, limit = 20).test {
                assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
                awaitItem() // success
                awaitComplete()
            }
        }

    @Test
    fun `network failure produces an error with NetworkError code`() =
        runBlocking {
            apiService.stub {
                on { pokemonList(limit = 20, offset = 0) } doAnswer { throw IOException("no connectivity") }
            }

            repository.pokemonList(offset = 0, limit = 20).test {
                assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
                val error = awaitItem() as Request.Error
                assertThat(error.apiError?.code).isEqualTo("NetworkError")
                awaitComplete()
            }
        }

    @Test
    fun `http 404 from api produces an error with the status code`() =
        runBlocking {
            val httpResponse = Response.error<PokemonListResponseDto>(404, "".toResponseBody())
            apiService.stub {
                on { pokemonList(limit = 20, offset = 0) } doAnswer { throw HttpException(httpResponse) }
            }

            repository.pokemonList(offset = 0, limit = 20).test {
                assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
                val error = awaitItem() as Request.Error
                assertThat(error.apiError?.code).isEqualTo("404")
                awaitComplete()
            }
        }

    @Test
    fun `response with no next page url is mapped to a page with hasNextPage false`() =
        runBlocking {
            val response = PokemonListResponseDto(
                count = 1,
                next = null,
                previous = null,
                results = listOf(PokemonItemDto("mew", "https://pokeapi.co/api/v2/pokemon/151/")),
            )
            whenever(apiService.pokemonList(limit = 20, offset = 0)).thenReturn(response)

            repository.pokemonList(offset = 0, limit = 20).test {
                assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
                val success = awaitItem() as Request.Success
                assertThat(success.data.hasNextPage).isFalse()
                awaitComplete()
            }
        }
}

package com.bonial.domain.useCase.pokemon

import app.cash.turbine.test
import com.bonial.domain.model.Pokemon
import com.bonial.domain.model.network.response.ApiError
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.repository.PokemonPage
import com.bonial.domain.repository.PokemonRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetPokemonListUseCaseTest {
    private val repository: PokemonRepository = mock()
    private val useCase = GetPokemonListUseCase(repository)

    @Test
    fun `fetching first page passes offset zero to the repository`() =
        runBlocking {
            val page = PokemonPage(
                pokemon = listOf(Pokemon(1, "Bulbasaur", "https://example.com/1.png")),
                totalCount = 151,
                hasNextPage = true,
            )
            whenever(repository.pokemonList(offset = 0, limit = 20)).thenReturn(flowOf(Request.Success(page)))

            useCase(GetPokemonListParams(offset = 0)).test {
                val success = awaitItem() as Request.Success
                assertThat(success.data.pokemon.first().name).isEqualTo("Bulbasaur")
                assertThat(success.data.totalCount).isEqualTo(151)
                assertThat(success.data.hasNextPage).isTrue()
                awaitComplete()
            }

            verify(repository).pokemonList(offset = 0, limit = 20)
        }

    @Test
    fun `fetching a subsequent page passes the correct offset to the repository`() =
        runBlocking {
            val page = PokemonPage(
                pokemon = listOf(Pokemon(21, "Spearow", "https://example.com/21.png")),
                totalCount = 151,
                hasNextPage = true,
            )
            whenever(repository.pokemonList(offset = 20, limit = 20)).thenReturn(flowOf(Request.Success(page)))

            useCase(GetPokemonListParams(offset = 20)).test {
                val success = awaitItem() as Request.Success
                assertThat(success.data.pokemon.first().name).isEqualTo("Spearow")
                awaitComplete()
            }

            verify(repository).pokemonList(offset = 20, limit = 20)
        }

    @Test
    fun `last page of results reports no next page available`() =
        runBlocking {
            val page = PokemonPage(
                pokemon = listOf(Pokemon(151, "Mew", "https://example.com/151.png")),
                totalCount = 151,
                hasNextPage = false,
            )
            whenever(repository.pokemonList(offset = 140, limit = 20)).thenReturn(flowOf(Request.Success(page)))

            useCase(GetPokemonListParams(offset = 140)).test {
                val success = awaitItem() as Request.Success
                assertThat(success.data.hasNextPage).isFalse()
                awaitComplete()
            }
        }

    @Test
    fun `network error from repository is passed through to the caller`() =
        runBlocking {
            whenever(repository.pokemonList(offset = 0, limit = 20)).thenReturn(
                flowOf(Request.Error(ApiError("NetworkError", "Check your internet connection and try again."))),
            )

            useCase(GetPokemonListParams()).test {
                val error = awaitItem() as Request.Error
                assertThat(error.apiError?.code).isEqualTo("NetworkError")
                assertThat(error.apiError?.message).contains("internet")
                awaitComplete()
            }
        }

    @Test
    fun `custom page size is forwarded to the repository`() =
        runBlocking {
            val page = PokemonPage(pokemon = emptyList(), totalCount = 0, hasNextPage = false)
            whenever(repository.pokemonList(offset = 0, limit = 10)).thenReturn(flowOf(Request.Success(page)))

            useCase(GetPokemonListParams(offset = 0, limit = 10)).test {
                awaitItem()
                awaitComplete()
            }

            verify(repository).pokemonList(offset = 0, limit = 10)
        }
}

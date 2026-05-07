package com.bonial.feature.pokemon.presentation

import app.cash.turbine.test
import com.bonial.domain.model.Pokemon
import com.bonial.domain.model.network.response.ApiError
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.repository.PokemonPage
import com.bonial.domain.useCase.pokemon.GetPokemonListParams
import com.bonial.domain.useCase.pokemon.GetPokemonListUseCase
import com.bonial.feature.pokemon.testing.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetPokemonListUseCase = mock()

    private fun buildViewModel() = PokemonListViewModel(useCase)

    @Test
    fun `initial state shows loading before the first page arrives`() =
        runTest {
            whenever(useCase(GetPokemonListParams(offset = 0))).thenReturn(
                flowOf(Request.Loading),
            )

            val viewModel = buildViewModel()

            viewModel.uiState.test {
                assertThat(awaitItem().isLoading).isTrue()
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `first page of pokemon is shown after a successful response`() =
        runTest {
            val page = PokemonPage(
                pokemon = listOf(
                    Pokemon(1, "Bulbasaur", "https://example.com/1.png"),
                    Pokemon(4, "Charmander", "https://example.com/4.png"),
                ),
                totalCount = 1302,
                hasNextPage = true,
            )
            whenever(useCase(GetPokemonListParams(offset = 0))).thenReturn(
                flowOf(Request.Loading, Request.Success(page)),
            )

            val viewModel = buildViewModel()

            viewModel.uiState.test {
                val finalState = awaitItem()
                assertThat(finalState.isLoading).isFalse()
                assertThat(finalState.pokemon).hasSize(2)
                assertThat(finalState.pokemon.map { it.name }).containsExactly("Bulbasaur", "Charmander")
                assertThat(finalState.hasNextPage).isTrue()
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `error message is shown when the first page request fails`() =
        runTest {
            whenever(useCase(GetPokemonListParams(offset = 0))).thenReturn(
                flowOf(
                    Request.Loading,
                    Request.Error(ApiError("NetworkError", "Check your internet connection and try again.")),
                ),
            )

            val viewModel = buildViewModel()

            viewModel.uiState.test {
                val finalState = awaitItem()
                assertThat(finalState.isLoading).isFalse()
                assertThat(finalState.error).contains("internet")
                assertThat(finalState.pokemon).isEmpty()
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `loading more appends the next page to the existing pokemon list`() =
        runTest {
            val firstPage = PokemonPage(
                pokemon = listOf(Pokemon(1, "Bulbasaur", "https://example.com/1.png")),
                totalCount = 1302,
                hasNextPage = true,
            )
            val secondPage = PokemonPage(
                pokemon = listOf(Pokemon(21, "Spearow", "https://example.com/21.png")),
                totalCount = 1302,
                hasNextPage = true,
            )
            whenever(useCase(GetPokemonListParams(offset = 0))).thenReturn(
                flowOf(Request.Loading, Request.Success(firstPage)),
            )
            whenever(useCase(GetPokemonListParams(offset = 1))).thenReturn(
                flowOf(Request.Loading, Request.Success(secondPage)),
            )

            val viewModel = buildViewModel()
            viewModel.loadMore()

            viewModel.uiState.test {
                val finalState = awaitItem()
                assertThat(finalState.pokemon).hasSize(2)
                assertThat(finalState.pokemon.map { it.name }).containsExactly("Bulbasaur", "Spearow")
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `load more does nothing when there is no next page`() =
        runTest {
            val lastPage = PokemonPage(
                pokemon = listOf(Pokemon(151, "Mew", "https://example.com/151.png")),
                totalCount = 151,
                hasNextPage = false,
            )
            whenever(useCase(GetPokemonListParams(offset = 0))).thenReturn(
                flowOf(Request.Loading, Request.Success(lastPage)),
            )

            val viewModel = buildViewModel()
            viewModel.loadMore()

            viewModel.uiState.test {
                val finalState = awaitItem()
                assertThat(finalState.pokemon).hasSize(1)
                assertThat(finalState.hasNextPage).isFalse()
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `retry reloads the first page and clears the previous error`() =
        runTest {
            val error = Request.Error(ApiError("NetworkError", "Check your internet connection and try again."))
            val success = Request.Success(
                PokemonPage(
                    pokemon = listOf(Pokemon(1, "Bulbasaur", "https://example.com/1.png")),
                    totalCount = 1302,
                    hasNextPage = true,
                ),
            )
            whenever(useCase(GetPokemonListParams(offset = 0)))
                .thenReturn(flowOf(Request.Loading, error))
                .thenReturn(flowOf(Request.Loading, success))

            val viewModel = buildViewModel()
            viewModel.retry()

            viewModel.uiState.test {
                val finalState = awaitItem()
                assertThat(finalState.error).isNull()
                assertThat(finalState.pokemon).hasSize(1)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `offset advances by the number of items on the current page`() =
        runTest {
            val page = PokemonPage(
                pokemon = List(20) { i -> Pokemon(i + 1, "Pokemon${i + 1}", "https://example.com/${i + 1}.png") },
                totalCount = 1302,
                hasNextPage = true,
            )
            whenever(useCase(GetPokemonListParams(offset = 0))).thenReturn(
                flowOf(Request.Loading, Request.Success(page)),
            )

            val viewModel = buildViewModel()

            viewModel.uiState.test {
                val finalState = awaitItem()
                assertThat(finalState.currentOffset).isEqualTo(20)
                cancelAndIgnoreRemainingEvents()
            }
        }
}

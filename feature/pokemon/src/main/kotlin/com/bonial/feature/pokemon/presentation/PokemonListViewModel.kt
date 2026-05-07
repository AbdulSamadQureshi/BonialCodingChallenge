package com.bonial.feature.pokemon.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.useCase.pokemon.GetPokemonListParams
import com.bonial.domain.useCase.pokemon.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PokemonListUiState(
    val pokemon: List<PokemonUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val hasNextPage: Boolean = false,
    val currentOffset: Int = 0,
)

@HiltViewModel
class PokemonListViewModel
    @Inject
    constructor(
        private val getPokemonListUseCase: GetPokemonListUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(PokemonListUiState())
        val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

        init {
            loadPokemon()
        }

        fun loadPokemon() {
            viewModelScope.launch {
                getPokemonListUseCase(GetPokemonListParams(offset = 0)).collect { request ->
                    when (request) {
                        is Request.Loading ->
                            _uiState.update {
                                it.copy(isLoading = true, error = null)
                            }
                        is Request.Success ->
                            _uiState.update {
                                it.copy(
                                    pokemon = request.data.pokemon.map { p -> p.toUiModel() },
                                    isLoading = false,
                                    hasNextPage = request.data.hasNextPage,
                                    currentOffset = request.data.pokemon.size,
                                    error = null,
                                )
                            }
                        is Request.Error ->
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    error = request.apiError?.message ?: "An unexpected error occurred.",
                                )
                            }
                    }
                }
            }
        }

        fun loadMore() {
            val state = _uiState.value
            if (state.isLoadingMore || !state.hasNextPage) return

            viewModelScope.launch {
                getPokemonListUseCase(GetPokemonListParams(offset = state.currentOffset)).collect { request ->
                    when (request) {
                        is Request.Loading ->
                            _uiState.update { it.copy(isLoadingMore = true) }
                        is Request.Success ->
                            _uiState.update { current ->
                                current.copy(
                                    pokemon = current.pokemon + request.data.pokemon.map { p -> p.toUiModel() },
                                    isLoadingMore = false,
                                    hasNextPage = request.data.hasNextPage,
                                    currentOffset = current.currentOffset + request.data.pokemon.size,
                                    error = null,
                                )
                            }
                        is Request.Error ->
                            _uiState.update {
                                it.copy(
                                    isLoadingMore = false,
                                    error = request.apiError?.message ?: "An unexpected error occurred.",
                                )
                            }
                    }
                }
            }
        }

        fun retry() = loadPokemon()
    }

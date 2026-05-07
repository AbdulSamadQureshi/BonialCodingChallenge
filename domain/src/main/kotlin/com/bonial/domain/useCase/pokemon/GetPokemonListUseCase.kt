package com.bonial.domain.useCase.pokemon

import com.bonial.domain.model.network.response.Request
import com.bonial.domain.repository.PokemonPage
import com.bonial.domain.repository.PokemonRepository
import com.bonial.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class GetPokemonListParams(
    val offset: Int = 0,
    val limit: Int = PokemonRepository.DEFAULT_PAGE_SIZE,
)

class GetPokemonListUseCase
    @Inject
    constructor(
        private val repository: PokemonRepository,
    ) : BaseUseCase<GetPokemonListParams, Flow<Request<PokemonPage>>> {
        override suspend fun invoke(params: GetPokemonListParams): Flow<Request<PokemonPage>> =
            repository.pokemonList(params.offset, params.limit)
    }

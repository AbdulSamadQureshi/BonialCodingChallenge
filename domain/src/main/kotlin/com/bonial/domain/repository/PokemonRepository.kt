package com.bonial.domain.repository

import com.bonial.domain.model.Pokemon
import com.bonial.domain.model.network.response.Request
import kotlinx.coroutines.flow.Flow

data class PokemonPage(
    val pokemon: List<Pokemon>,
    val totalCount: Int,
    val hasNextPage: Boolean,
)

interface PokemonRepository {
    fun pokemonList(offset: Int, limit: Int = DEFAULT_PAGE_SIZE): Flow<Request<PokemonPage>>

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}

package com.bonial.data.repository

import com.bonial.data.mapper.toDomainPage
import com.bonial.data.remote.service.PokemonApiService
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.repository.PokemonPage
import com.bonial.domain.repository.PokemonRepository
import com.bonial.domain.utils.mapSuccess
import com.bonial.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl
    @Inject
    constructor(
        private val apiService: PokemonApiService,
    ) : PokemonRepository {
        override fun pokemonList(offset: Int, limit: Int): Flow<Request<PokemonPage>> =
            safeApiCall { apiService.pokemonList(limit = limit, offset = offset) }.map { request ->
                request.mapSuccess { it.toDomainPage() }
            }
    }

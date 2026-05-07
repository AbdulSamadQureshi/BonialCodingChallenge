package com.bonial.data.remote.service

import com.bonial.data.remote.model.PokemonListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon")
    suspend fun pokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): PokemonListResponseDto
}

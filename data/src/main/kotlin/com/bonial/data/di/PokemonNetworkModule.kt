package com.bonial.data.di

import com.bonial.data.remote.service.PokemonApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonNetworkModule {
    private const val POKEMON_BASE_URL = "https://pokeapi.co/api/v2/"

    // PokéAPI is a public API that requires no authentication, so we build a standalone
    // Retrofit instead of reusing the primary RetrofitClient (which injects auth headers).
    @Provides
    @Singleton
    @Named("pokemon")
    fun providePokemonRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(POKEMON_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    @Provides
    @Singleton
    fun providePokemonApiService(
        @Named("pokemon") retrofit: Retrofit,
    ): PokemonApiService = retrofit.create(PokemonApiService::class.java)
}

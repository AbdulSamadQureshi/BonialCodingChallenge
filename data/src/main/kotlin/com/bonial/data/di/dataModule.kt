package com.bonial.data.di

import com.bonial.data.remote.service.*
import com.bonial.data.repository.*
import com.bonial.domain.repository.*
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    // Services
    single<BrochuresApiService> { get<Retrofit>().create(BrochuresApiService::class.java) }

    // Repositories
    single<BrochuresRepository> { BrochuresRepositoryImpl(get()) }

    // Local Storage
    single<LocalStorageRepository> { LocalStorageRepositoryImpl(get()) }
}

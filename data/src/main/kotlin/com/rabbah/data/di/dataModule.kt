package com.rabbah.data.di

import com.rabbah.data.remote.service.*
import com.rabbah.data.remote.service.implementation.*
import com.rabbah.data.repository.*
import com.rabbah.domain.repository.*
import org.koin.dsl.module

val dataModule = module {
    // Services
    single<BrochuresApiService> { BrochuresApiServiceImpl(get()) }

    // Repositories
    single<BrochuresRepository> { BrochuresRepositoryImpl(get()) }

    // Local Storage
    single<LocalStorageRepository> { LocalStorageRepositoryImpl(get()) }
}
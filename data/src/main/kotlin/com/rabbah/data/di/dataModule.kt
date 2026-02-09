package com.rabbah.data.di

import com.rabbah.data.remote.service.*
import com.rabbah.data.remote.service.implementation.*
import com.rabbah.data.repository.*
import com.rabbah.domain.repository.*
import org.koin.dsl.module

val dataModule = module {
    // Services
    single<AppApiService> { AppApiServiceImpl(get()) }
    single<AuthApiService> { AuthApiServiceImpl(get()) }
    single<OffersApiService> { OffersApiServiceImpl(get()) }
    single<TransactionApiService> { TransactionApiServiceImpl(get()) }
    single<UserApiService> { UserApiServiceImpl(get()) }
    single<VendApiService> { VendApiServiceImpl(get()) }
    single<WalletApiService> { WalletApiServiceImpl(get()) }

    // Repositories
    single<AppRepository> { AppRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<OffersRepository> { OffersRepositoryImpl(get()) }
    single<TransactionRepository> { TransactionRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<VendRepository> { VendRepositoryImpl(get()) }
    single<WalletRepository> { WalletRepositoryImpl(get()) }
    single<LocalStorageRepository> { LocalStorageRepositoryImpl(get()) }

    // Local Storage

}
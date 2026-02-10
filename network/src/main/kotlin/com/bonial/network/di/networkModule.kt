package com.bonial.network.di

import com.bonial.network.RetrofitClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

fun createNetworkModule(baseUrl: String, enableLogging: Boolean): Module = module {
    single<Retrofit> { RetrofitClient(baseUrl, enableLogging, get()).retrofit }
}
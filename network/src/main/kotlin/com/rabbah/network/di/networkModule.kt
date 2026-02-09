package com.rabbah.network.di

import com.rabbah.core.preferences.SharedPrefsManager
import com.rabbah.network.KtorClient
import org.koin.core.module.Module
import org.koin.dsl.module

fun createNetworkModule(baseUrl: String, enableLogging: Boolean): Module = module {
    single { KtorClient(baseUrl, enableLogging, get()).client }
}
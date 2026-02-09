package com.rabbah.core.di

import com.rabbah.core.preferences.SharedPrefsManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { SharedPrefsManager(androidContext()) }
}

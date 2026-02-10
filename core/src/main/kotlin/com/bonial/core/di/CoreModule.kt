package com.bonial.core.di

import com.bonial.core.preferences.SharedPrefsManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { SharedPrefsManager(androidContext()) }
}

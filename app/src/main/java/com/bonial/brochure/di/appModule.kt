package com.bonial.brochure.di

import com.bonial.brochure.BuildConfig
import com.bonial.core.preferences.SharedPrefsManager
import com.bonial.network.RetrofitClient
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@ContributesTo(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(sharedPrefsManager: SharedPrefsManager): Retrofit {
        return RetrofitClient(
            baseUrl = BuildConfig.BASE_URL,
            enableLogging = BuildConfig.DEBUG,
            sharedPrefsManager = sharedPrefsManager
        ).retrofit
    }
}

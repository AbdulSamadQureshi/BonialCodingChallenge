package com.bonial.data.di

import com.bonial.data.remote.service.BrochuresApiService
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
object DataModule {

    @Provides
    @Singleton
    fun provideBrochuresApiService(retrofit: Retrofit): BrochuresApiService {
        return retrofit.create(BrochuresApiService::class.java)
    }
}

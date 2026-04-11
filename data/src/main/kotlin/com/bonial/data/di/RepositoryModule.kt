package com.bonial.data.di

import com.bonial.data.repository.BrochuresRepositoryImpl
import com.bonial.data.repository.LocalStorageRepositoryImpl
import com.bonial.domain.repository.BrochuresRepository
import com.bonial.domain.repository.LocalStorageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBrochuresRepository(impl: BrochuresRepositoryImpl): BrochuresRepository

    @Binds
    @Singleton
    abstract fun bindLocalStorageRepository(impl: LocalStorageRepositoryImpl): LocalStorageRepository
}
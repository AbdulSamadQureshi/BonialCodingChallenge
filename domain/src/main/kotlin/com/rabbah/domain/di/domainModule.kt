package com.rabbah.domain.di

import com.rabbah.domain.useCase.brochures.BrochuresUseCase
import org.koin.dsl.module

val domainModule = module {
    // brochures
    factory { BrochuresUseCase(get()) }
}

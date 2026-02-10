package com.bonial.domain.di

import com.bonial.domain.useCase.brochures.BrochuresUseCase
import org.koin.dsl.module

val domainModule = module {
    // brochures
    factory { BrochuresUseCase(get()) }
}

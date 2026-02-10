package com.bonial.brochure.di

import com.bonial.brochure.presentation.home.BrochuresViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { BrochuresViewModel(get()) }
}

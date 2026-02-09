package com.rabbah.clw.di

import com.rabbah.clw.presentation.home.BrochuresViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { BrochuresViewModel(get()) }
}

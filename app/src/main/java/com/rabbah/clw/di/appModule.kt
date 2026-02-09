package com.rabbah.clw.di

import com.rabbah.clw.presentation.accountDetail.AccountDetailViewModel
import com.rabbah.clw.presentation.home.HomeViewModel
import com.rabbah.clw.presentation.login.LoginViewModel
import com.rabbah.clw.presentation.more.ProfileViewModel
import com.rabbah.clw.presentation.nearbyVend.NearbyVendViewModel
import com.rabbah.clw.presentation.offers.OffersViewModel
import com.rabbah.clw.presentation.transactionDetail.TransactionDetailViewModel
import com.rabbah.clw.presentation.transactions.TransactionsViewModel
import com.rabbah.clw.presentation.wallet.WalletViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { HomeViewModel(get(), get(), get(), get()) }
    viewModel { TransactionDetailViewModel(get()) }
    viewModel { WalletViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { TransactionsViewModel(get()) }
    viewModel { AccountDetailViewModel(get(), get(), get()) }
    viewModel { OffersViewModel(get(), get()) }
    viewModel { NearbyVendViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}

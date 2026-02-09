package com.rabbah.domain.di

import com.rabbah.domain.useCase.app.AppVersionUseCase
import com.rabbah.domain.useCase.brochures.HomeOfferUseCase
import com.rabbah.domain.useCase.transaction.TransactionDetailUseCase
import com.rabbah.domain.useCase.transaction.TransactionHistoryUseCase
import com.rabbah.domain.useCase.user.AccountDetailsUseCase
import com.rabbah.domain.useCase.user.UpdateAccountDetailsUseCase
import com.rabbah.domain.useCase.user.UpdateProfilePictureUseCase
import com.rabbah.domain.useCase.vend.NearbyVendsUseCase
import com.rabbah.domain.useCase.wallet.ActivateCardUseCase
import com.rabbah.domain.useCase.wallet.LockCardUseCase
import com.rabbah.domain.useCase.wallet.RequestCardUseCase
import com.rabbah.domain.useCase.wallet.UnlockCardUseCase
import com.rabbah.domain.useCase.wallet.WalletUseCase
import org.koin.dsl.module

val domainModule = module {

    // App
    factory { AppVersionUseCase(get()) }

    // Auth
    factory { LoginUseCase(get()) }
    factory { RequestOtpUseCase(get()) }
    factory { VerifyOtpUseCase(get()) }

    // Offers
    factory { ActiveOffersUseCase(get()) }
    factory { ExpiredOffersUseCase(get()) }
    factory { HomeOfferUseCase(get()) }

    // Transaction
    factory { TransactionDetailUseCase(get()) }
    factory { TransactionHistoryUseCase(get()) }

    // User
    factory { AccountDetailsUseCase(get()) }
    factory { UpdateAccountDetailsUseCase(get()) }
    factory { UpdateProfilePictureUseCase(get()) }

    // Vend
    factory { NearbyVendsUseCase(get()) }

    // Wallet
    factory { LockCardUseCase(get()) }
    factory { RequestCardUseCase(get()) }
    factory { ActivateCardUseCase(get()) }
    factory { UnlockCardUseCase(get()) }
    factory { WalletUseCase(get()) }

    // local storage
    factory { SaveUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
    factory { LogoutUserUseCase(get()) }

}

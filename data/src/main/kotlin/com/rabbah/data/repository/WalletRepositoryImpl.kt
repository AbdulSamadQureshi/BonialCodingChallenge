package com.rabbah.data.repository

import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class WalletRepositoryImpl(private val walletApiService: WalletApiService) : WalletRepository {
    override fun wallet(userId: Int): Flow<Request<SingleBaseDto<WalletDto>>> {
        return safeApiCall { walletApiService.wallet(userId) }
    }

    override fun lockCard(userId: Int): Flow<Request<SingleBaseDto<CardDto>>> {
        return safeApiCall { walletApiService.lockCard(userId) }
    }

    override fun unlockCard(userId: Int): Flow<Request<SingleBaseDto<CardDto>>> {
        return safeApiCall { walletApiService.unlockCard(userId) }
    }

    override fun activateCard(userId: Int): Flow<Request<SingleBaseDto<CardDto>>> {
                return safeApiCall { walletApiService.activateCard(userId) }
    }

    override fun requestCard(userId: Int): Flow<Request<SingleBaseDto<CardDto>>> {
        return safeApiCall { walletApiService.requestCard(userId) }
    }
}

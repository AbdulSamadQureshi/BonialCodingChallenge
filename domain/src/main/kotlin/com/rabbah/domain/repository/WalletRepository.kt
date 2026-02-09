package com.rabbah.domain.repository

import com.rabbah.domain.model.network.response.CardDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.WalletDto
import kotlinx.coroutines.flow.Flow

interface WalletRepository {
    fun wallet(userId: Int): Flow<Request<SingleBaseDto<WalletDto>>>
    fun lockCard(userId: Int): Flow<Request<SingleBaseDto<CardDto>>>
    fun unlockCard(userId: Int): Flow<Request<SingleBaseDto<CardDto>>>
    fun activateCard(userId: Int): Flow<Request<SingleBaseDto<CardDto>>>
    fun requestCard(userId: Int): Flow<Request<SingleBaseDto<CardDto>>>
}

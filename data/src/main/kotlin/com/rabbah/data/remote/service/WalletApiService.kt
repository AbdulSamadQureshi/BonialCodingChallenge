package com.rabbah.data.remote.service

import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.CardDto
import com.rabbah.domain.model.network.response.WalletDto

interface WalletApiService {
    suspend fun wallet(userId: Int): SingleBaseDto<WalletDto>
    suspend fun lockCard(userId: Int): SingleBaseDto<CardDto>
    suspend fun unlockCard(userId: Int): SingleBaseDto<CardDto>
    suspend fun activateCard(userId: Int): SingleBaseDto<CardDto>
    suspend fun requestCard(userId: Int): SingleBaseDto<CardDto>
}
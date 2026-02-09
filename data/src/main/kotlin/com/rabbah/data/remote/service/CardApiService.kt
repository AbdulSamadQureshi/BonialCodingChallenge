package com.rabbah.data.remote.service

import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.CardDto

interface CardApiService {
    suspend fun lockCard(userId: Int): SingleBaseDto<CardDto>
    suspend fun unlockCard(userId: Int): SingleBaseDto<CardDto>
    suspend fun suspendCard(userId: Int): SingleBaseDto<CardDto>
    suspend fun requestCard(userId: Int): SingleBaseDto<CardDto>
}
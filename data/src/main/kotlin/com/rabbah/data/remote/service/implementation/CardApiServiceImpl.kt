package com.rabbah.data.remote.service.implementation

import com.rabbah.data.remote.service.CardApiService
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.CardDto

class CardApiServiceImpl: CardApiService {
    override suspend fun lockCard(userId: Int): SingleBaseDto<CardDto> {
        TODO("Not yet implemented")
    }

    override suspend fun unlockCard(userId: Int): SingleBaseDto<CardDto> {
        TODO("Not yet implemented")
    }

    override suspend fun suspendCard(userId: Int): SingleBaseDto<CardDto> {
        TODO("Not yet implemented")
    }

    override suspend fun requestCard(userId: Int): SingleBaseDto<CardDto> {
        TODO("Not yet implemented")
    }

}
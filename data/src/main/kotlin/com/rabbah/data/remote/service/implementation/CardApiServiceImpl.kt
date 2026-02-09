package com.rabbah.data.remote.service.implementation

import com.rabbah.domain.model.network.response.SingleBaseDto

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
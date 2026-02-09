package com.rabbah.data.remote.service

import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.OfferDto

interface OffersApiService {
    suspend fun homeOffer(userId: Int): MultiBaseDto<OfferDto>
    suspend fun activeOffers(userId: Int, pageNumber: Int = 1): MultiBaseDto<OfferDto>
    suspend fun expiredOffers(userId: Int, pageNumber: Int = 1): MultiBaseDto<OfferDto>
}
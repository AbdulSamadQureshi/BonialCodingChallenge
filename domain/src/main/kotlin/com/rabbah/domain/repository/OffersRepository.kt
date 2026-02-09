package com.rabbah.domain.repository

import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.OfferDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import kotlinx.coroutines.flow.Flow

interface OffersRepository {
    fun homeOffer(userId: Int): Flow<Request<MultiBaseDto<OfferDto>>>
    fun activeOffers(userId: Int, pageNumber: Int = 1): Flow<Request<MultiBaseDto<OfferDto>>>
    fun expiredOffers(userId: Int, pageNumber: Int = 1): Flow<Request<MultiBaseDto<OfferDto>>>
}

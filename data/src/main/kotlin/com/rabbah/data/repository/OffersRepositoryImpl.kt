package com.rabbah.data.repository

import com.rabbah.data.remote.service.OffersApiService
import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.OfferDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.repository.OffersRepository
import com.rabbah.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class OffersRepositoryImpl(private val offersApiService: OffersApiService): OffersRepository {
    override fun homeOffer(userId: Int): Flow<Request<MultiBaseDto<OfferDto>>> {
        return safeApiCall { offersApiService.homeOffer(userId) }
    }

    override fun activeOffers(userId: Int, pageNumber: Int): Flow<Request<MultiBaseDto<OfferDto>>> {
        return safeApiCall { offersApiService.activeOffers(userId, pageNumber) }
    }

    override fun expiredOffers(userId: Int, pageNumber: Int): Flow<Request<MultiBaseDto<OfferDto>>> {
        return safeApiCall { offersApiService.expiredOffers(userId, pageNumber) }
    }
}
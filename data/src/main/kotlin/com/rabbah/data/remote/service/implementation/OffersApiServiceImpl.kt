package com.rabbah.data.remote.service.implementation

import com.rabbah.data.remote.service.OffersApiService
import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.OfferDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class OffersApiServiceImpl(private val client: HttpClient): OffersApiService {

    override suspend fun homeOffer(userId: Int): MultiBaseDto<OfferDto> {
        // Assuming an endpoint named "homeOffer"
        return client.get("homeOffer") {
            parameter("userId", userId)
        }.body()
    }

    override suspend fun activeOffers(userId: Int, pageNumber: Int): MultiBaseDto<OfferDto> {
        return client.get("activeOffers") {
            parameter("userId", userId)
            parameter("page", pageNumber)
        }.body()
    }

    override suspend fun expiredOffers(userId: Int, pageNumber: Int): MultiBaseDto<OfferDto> {
        return client.get("expiredOffers") {
            parameter("userId", userId)
            parameter("page", pageNumber)
        }.body()
    }

}

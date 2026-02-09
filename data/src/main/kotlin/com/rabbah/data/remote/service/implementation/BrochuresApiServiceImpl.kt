package com.rabbah.data.remote.service.implementation

import com.rabbah.data.remote.service.BrochuresApiService
import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.BrochureDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class BrochuresApiServiceImpl(private val client: HttpClient): BrochuresApiService {

    override suspend fun brochures(): MultiBaseDto<BrochureDto> {
        // Assuming an endpoint named "homeOffer"
        return client.get("homeOffer") {
        }.body()
    }

}

package com.rabbah.data.remote.service.implementation

import com.rabbah.data.remote.service.BrochuresApiService
import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.BrochureDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BrochuresApiServiceImpl(private val client: HttpClient): BrochuresApiService {

    override suspend fun brochures(): MultiBaseDto<BrochureDto> {
        // Assuming an endpoint named "brochures"
        return client.get("brochures") {
        }.body()
    }

}

package com.rabbah.data.remote.service.implementation

import com.rabbah.data.remote.service.VendApiService
import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.PaginatedDto
import com.rabbah.domain.model.network.response.VendDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class VendApiServiceImpl(private val client: HttpClient) : VendApiService {
    override suspend fun nearbyVends(
        query: String,
        latitude: Double,
        longitude: Double,
        page: Int
    ): MultiBaseDto<VendDto> {
        return client.get("nearbyVends") {
            parameter("page", page)
            parameter("query", query)
            parameter("latitude", latitude)
            parameter("longitude", longitude)
        }.body()
    }
}

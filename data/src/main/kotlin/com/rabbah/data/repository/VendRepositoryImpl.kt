package com.rabbah.data.repository

import com.rabbah.data.remote.service.VendApiService
import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.PaginatedDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.VendDto
import com.rabbah.domain.repository.VendRepository
import com.rabbah.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class VendRepositoryImpl(private val vendApiService: VendApiService) : VendRepository {
    override fun nearbyVends(
        query: String,
        latitude: Double,
        longitude: Double,
        page: Int
    ): Flow<Request<MultiBaseDto<VendDto>>> {
        return safeApiCall {
            vendApiService.nearbyVends(
                query = query,
                page = page,
                latitude = latitude,
                longitude = longitude
            )
        }
    }
}

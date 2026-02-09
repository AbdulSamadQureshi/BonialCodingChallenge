package com.rabbah.data.remote.service

import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.PaginatedDto
import com.rabbah.domain.model.network.response.VendDto

interface VendApiService {
    suspend fun nearbyVends(query: String, latitude: Double, longitude: Double, page: Int): MultiBaseDto<VendDto>
}

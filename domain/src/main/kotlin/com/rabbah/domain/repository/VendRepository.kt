package com.rabbah.domain.repository

import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.PaginatedDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.VendDto
import kotlinx.coroutines.flow.Flow

interface VendRepository {
    fun nearbyVends(query: String, latitude: Double, longitude: Double, page: Int): Flow<Request<MultiBaseDto<VendDto>>>
}

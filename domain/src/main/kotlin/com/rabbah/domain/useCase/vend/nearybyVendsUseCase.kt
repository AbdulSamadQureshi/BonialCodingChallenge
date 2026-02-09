package com.rabbah.domain.useCase.vend

import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.PaginatedDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.VendDto
import com.rabbah.domain.repository.VendRepository
import com.rabbah.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow

data class NearbyVendRequest(
    val query: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val page: Int = 1,
)

class NearbyVendsUseCase(
    private val repository: VendRepository
) : BaseUseCase<Any?, Flow<Request<MultiBaseDto<VendDto>>>> {
    override suspend fun invoke(params: Any?): Flow<Request<MultiBaseDto<VendDto>>> {
        val request = params as NearbyVendRequest
        // Ignoring page from request for now as per instructions
        return repository.nearbyVends(
            latitude = request.latitude,
            longitude = request.longitude,
            query = request.query,
            page = 1 // Hardcoding page to 1 to disable pagination effectively from the usecase side while keeping the signature
        )
    }
}

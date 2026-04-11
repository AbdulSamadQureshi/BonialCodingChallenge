package com.bonial.domain.useCase.brochures

import com.bonial.domain.model.Brochure
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.repository.BrochuresRepository
import com.bonial.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val MAX_DISTANCE_KM = 5.0

class BrochuresUseCase @Inject constructor(
    private val repository: BrochuresRepository,
) : BaseUseCase<Any?, Flow<Request<List<Brochure>>>> {

    override suspend fun invoke(params: Any?): Flow<Request<List<Brochure>>> {
        return repository.brochures().map { request ->
            when (request) {
                is Request.Loading -> Request.Loading
                is Request.Error -> Request.Error(request.apiError)
                is Request.Success -> Request.Success(request.data.filter { it.isEligible() })
            }
        }
    }

    private fun Brochure.isEligible(): Boolean {
        val isCorrectType = contentType == "brochure" || contentType == "brochurePremium"
        val isNearby = (distance ?: Double.MAX_VALUE) < MAX_DISTANCE_KM
        return isCorrectType && isNearby
    }
}

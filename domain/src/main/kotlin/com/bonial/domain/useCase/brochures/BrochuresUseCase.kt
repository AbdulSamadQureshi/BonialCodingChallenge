package com.bonial.domain.useCase.brochures

import com.bonial.domain.model.Brochure
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.repository.BrochuresRepository
import com.bonial.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BrochuresUseCase(
    private val repository: BrochuresRepository
) : BaseUseCase<Any?, Flow<Request<List<Brochure>>>> {
    override suspend fun invoke(params: Any?): Flow<Request<List<Brochure>>> {
        return repository.brochures().map { response ->
            when (response) {
                is Request.Loading -> Request.Loading
                is Request.Error -> Request.Error(response.apiError)
                is Request.Success -> {
                    val contents = response.data.embedded?.contents ?: emptyList()
                    val domainBrochures = contents.flatMap { wrapper ->
                        val contentType = wrapper.contentType
                        wrapper.content.map { dto ->
                            Brochure(
                                title = dto.title,
                                coverUrl = dto.brochureImage,
                                distance = dto.distance,
                                publisherName = dto.publisher?.name,
                                contentType = contentType
                            )
                        }
                    }.filter { brochure ->
                        val isCorrectType = brochure.contentType == "brochure" || brochure.contentType == "brochurePremium"
                        val isNearby = (brochure.distance ?: Double.MAX_VALUE) < 5.0
                        isCorrectType && isNearby
                    }
                    Request.Success(domainBrochures)
                }
            }
        }
    }
}

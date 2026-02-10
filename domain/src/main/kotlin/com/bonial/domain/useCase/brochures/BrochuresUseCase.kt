package com.bonial.domain.useCase.brochures

import com.bonial.domain.model.network.response.BrochureResponseDto
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.repository.BrochuresRepository
import com.bonial.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow

class BrochuresUseCase(
    private val repository: BrochuresRepository
) : BaseUseCase<Any?, Flow<Request<BrochureResponseDto>>> {
    override suspend fun invoke(params: Any?): Flow<Request<BrochureResponseDto>> {
        return repository.brochures()
    }
}

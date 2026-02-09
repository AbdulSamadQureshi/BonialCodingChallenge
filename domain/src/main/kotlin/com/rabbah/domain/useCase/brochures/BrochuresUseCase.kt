package com.rabbah.domain.useCase.brochures

import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.BrochureDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.repository.BrochuresRepository
import com.rabbah.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow

class BrochuresUseCase(
    private val repository: BrochuresRepository
) : BaseUseCase<Any?, Flow<Request<MultiBaseDto<BrochureDto>>>> {
    override suspend fun invoke(params: Any?): Flow<Request<MultiBaseDto<BrochureDto>>> {
        return repository.brochures()
    }
}

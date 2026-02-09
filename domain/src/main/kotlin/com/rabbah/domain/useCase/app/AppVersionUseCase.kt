package com.rabbah.domain.useCase.app

import com.rabbah.domain.model.network.response.AppVersionDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.repository.AppRepository
import com.rabbah.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow

class AppVersionUseCase(
    private val repository: AppRepository
) : BaseUseCase<Any?, Flow<Request<SingleBaseDto<AppVersionDto>>>> {
    override suspend fun invoke(params: Any?): Flow<Request<SingleBaseDto<AppVersionDto>>> {
        return repository.getAppVersion()
    }
}

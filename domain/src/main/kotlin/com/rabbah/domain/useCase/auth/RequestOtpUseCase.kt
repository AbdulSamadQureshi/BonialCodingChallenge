package com.rabbah.domain.useCase.auth

import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.UserDto
import com.rabbah.domain.repository.AuthRepository
import com.rabbah.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow

class RequestOtpUseCase(
    private val repository: AuthRepository,
) : BaseUseCase<Any?, Flow<Request<SingleBaseDto<UserDto>>>> {
    override suspend fun invoke(params: Any?): Flow<Request<SingleBaseDto<UserDto>>> {
        return repository.requestOtp(params as String)
    }
}

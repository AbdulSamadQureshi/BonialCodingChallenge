package com.rabbah.domain.useCase.auth

import com.rabbah.domain.model.network.request.LoginRequest
import com.rabbah.domain.model.network.response.LoginDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.repository.AuthRepository
import com.rabbah.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val repository: AuthRepository,
) : BaseUseCase<Any?, Flow<Request<SingleBaseDto<LoginDto>>>> {
    override suspend fun invoke(params: Any?): Flow<Request<SingleBaseDto<LoginDto>>> {
        return repository.login(params as LoginRequest)
    }
}

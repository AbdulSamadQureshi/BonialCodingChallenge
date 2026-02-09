package com.rabbah.domain.useCase.user

import com.rabbah.domain.model.network.request.UpdateAccountRequest
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.UserDto
import com.rabbah.domain.repository.UserRepository
import com.rabbah.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow
class UpdateAccountDetailsUseCase(
    private val repository: UserRepository
) : BaseUseCase<Any?, Flow<Request<SingleBaseDto<UserDto>>>> {
    override suspend fun invoke(params: Any?): Flow<Request<SingleBaseDto<UserDto>>> {
        return repository.updateAccountDetails(params as UpdateAccountRequest)
    }
}

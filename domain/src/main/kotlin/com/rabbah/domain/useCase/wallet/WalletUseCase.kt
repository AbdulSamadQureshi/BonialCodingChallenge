package com.rabbah.domain.useCase.wallet

import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.WalletDto
import com.rabbah.domain.repository.WalletRepository
import com.rabbah.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow

class WalletUseCase(
    private val repository: WalletRepository
) : BaseUseCase<Any?, Flow<Request<SingleBaseDto<WalletDto>>>> {
    override suspend fun invoke(params: Any?): Flow<Request<SingleBaseDto<WalletDto>>> {
        return repository.wallet(params as Int)
    }
}

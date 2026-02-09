package com.rabbah.domain.useCase.transaction

import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.TransactionDetailDto
import com.rabbah.domain.repository.TransactionRepository
import com.rabbah.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow

class TransactionDetailUseCase(
    private val repository: TransactionRepository
) : BaseUseCase<Any?, Flow<Request<SingleBaseDto<TransactionDetailDto>>>> {
    override suspend fun invoke(params: Any?): Flow<Request<SingleBaseDto<TransactionDetailDto>>> {
        val (userId, transactionId) = params as Pair<Int, Int>
        return repository.transactionDetails(userId, transactionId)
    }
}

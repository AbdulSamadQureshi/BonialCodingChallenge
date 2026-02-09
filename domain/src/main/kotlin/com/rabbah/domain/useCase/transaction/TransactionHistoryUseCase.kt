package com.rabbah.domain.useCase.transaction

import com.rabbah.domain.model.network.request.TransactionsRequest
import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.TransactionDto
import com.rabbah.domain.repository.TransactionRepository
import com.rabbah.domain.useCase.BaseUseCase
import kotlinx.coroutines.flow.Flow

class TransactionHistoryUseCase(
    private val repository: TransactionRepository
) : BaseUseCase<Any?, Flow<Request<MultiBaseDto<TransactionDto>>>> {
    override suspend fun invoke(params: Any?): Flow<Request<MultiBaseDto<TransactionDto>>> {
        return repository.transactionHistory(params as TransactionsRequest)
    }
}

package com.rabbah.data.repository

import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(private val transactionApiService: TransactionApiService) :
    TransactionRepository {
    override fun transactionHistory(transactionsRequest: TransactionsRequest): Flow<Request<MultiBaseDto<TransactionDto>>> {
        return safeApiCall { transactionApiService.transactionHistory(transactionsRequest) }
    }

    override fun transactionDetails(
        userId: Int,
        transactionId: Int
    ): Flow<Request<SingleBaseDto<TransactionDetailDto>>> {
        return safeApiCall { transactionApiService.transactionDetails(userId, transactionId) }
    }
}
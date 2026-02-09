package com.rabbah.data.repository

import com.rabbah.data.remote.service.TransactionApiService
import com.rabbah.domain.model.network.request.TransactionsRequest
import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.TransactionDetailDto
import com.rabbah.domain.model.network.response.TransactionDto
import com.rabbah.domain.repository.TransactionRepository
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
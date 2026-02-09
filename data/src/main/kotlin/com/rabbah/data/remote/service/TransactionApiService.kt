package com.rabbah.data.remote.service

import com.rabbah.domain.model.network.request.TransactionsRequest
import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.TransactionDetailDto
import com.rabbah.domain.model.network.response.TransactionDto

interface TransactionApiService {
    suspend fun transactionHistory(transactionsRequest: TransactionsRequest): MultiBaseDto<TransactionDto>
    suspend fun transactionDetails(userId: Int, transactionId: Int): SingleBaseDto<TransactionDetailDto>
}

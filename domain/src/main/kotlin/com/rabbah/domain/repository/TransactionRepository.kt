package com.rabbah.domain.repository

import com.rabbah.domain.model.network.request.TransactionsRequest
import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.TransactionDetailDto
import com.rabbah.domain.model.network.response.TransactionDto
import kotlinx.coroutines.flow.Flow


interface TransactionRepository {
    fun transactionHistory(transactionsRequest: TransactionsRequest): Flow<Request<MultiBaseDto<TransactionDto>>>
    fun transactionDetails(userId: Int, transactionId: Int): Flow<Request<SingleBaseDto<TransactionDetailDto>>>
}

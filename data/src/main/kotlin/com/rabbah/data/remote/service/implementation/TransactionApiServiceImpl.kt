package com.rabbah.data.remote.service.implementation

import com.rabbah.domain.model.network.response.MultiBaseDto
import com.rabbah.domain.model.network.response.SingleBaseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody

class TransactionApiServiceImpl(private val client: HttpClient) : TransactionApiService {

    override suspend fun transactionHistory(transactionsRequest: TransactionsRequest): MultiBaseDto<TransactionDto> {
        return client.get("v1/merchant-partners/transactions") {
            setBody(transactionsRequest)
        }.body()
    }

    override suspend fun transactionDetails(
        userId: Int,
        transactionId: Int
    ): SingleBaseDto<TransactionDetailDto> {
        return client.get("v1/merchant-partners/transactions") {
            parameter("id", transactionId)
        }.body()
    }

}

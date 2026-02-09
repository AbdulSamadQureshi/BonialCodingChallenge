package com.rabbah.data.remote.service.implementation

import com.rabbah.domain.model.network.response.SingleBaseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post

class WalletApiServiceImpl(private val client: HttpClient) : WalletApiService {

    override suspend fun wallet(userId: Int): SingleBaseDto<WalletDto> {
        return client.get("v1/merchant-partners/wallet/ledger") {
        }.body()
    }

    override suspend fun lockCard(userId: Int): SingleBaseDto<CardDto> {
        return client.post("lockCard") {
            parameter("userId", userId)
        }.body()
    }

    override suspend fun unlockCard(userId: Int): SingleBaseDto<CardDto> {
        return client.post("unlockCard") {
            parameter("userId", userId)
        }.body()
    }

    override suspend fun activateCard(userId: Int): SingleBaseDto<CardDto> {
        return client.post("activateCard") {
            parameter("userId", userId)
        }.body()
    }

    override suspend fun requestCard(userId: Int): SingleBaseDto<CardDto> {
        // Assuming an endpoint named "requestCard"
        return client.post("requestCard") {
            parameter("userId", userId)
        }.body()
    }
}

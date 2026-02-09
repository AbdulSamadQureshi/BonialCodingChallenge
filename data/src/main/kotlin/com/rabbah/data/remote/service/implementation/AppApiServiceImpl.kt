package com.rabbah.data.remote.service.implementation

import com.rabbah.domain.model.network.response.SingleBaseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class AppApiServiceImpl(private val client: HttpClient) : AppApiService {

    override suspend fun getAppVersion(): SingleBaseDto<AppVersionDto> {
        return client.get("appVersion") {
        }.body()
    }

}

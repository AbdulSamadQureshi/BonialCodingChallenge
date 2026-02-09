package com.rabbah.data.remote.service.implementation

import com.rabbah.data.remote.service.UserApiService
import com.rabbah.domain.model.network.request.UpdateAccountRequest
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class UserApiServiceImpl(private val client: HttpClient): UserApiService {

    override suspend fun accountDetails(userId: Int): SingleBaseDto<UserDto> {
        return client.get("v1/merchant-partners/users") {
        }.body()
    }

    override suspend fun updateAccountDetails(updateAccountRequest: UpdateAccountRequest): SingleBaseDto<UserDto> {
        return client.patch("v1/merchant-partners/users") {
            setBody(updateAccountRequest)
        }.body()
    }

    override suspend fun updateProfilePicture(userId: Int, profilePictureUrl: String): SingleBaseDto<UserDto> {
        return client.post("v1/merchant-partners/users/profile-image") {
            parameter("profilePictureUrl", profilePictureUrl)
        }.body()
    }
}

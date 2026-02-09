package com.rabbah.data.remote.service

import com.rabbah.domain.model.network.request.UpdateAccountRequest
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.UserDto

interface UserApiService {
    suspend fun accountDetails(userId: Int): SingleBaseDto<UserDto>
    suspend fun updateAccountDetails(updateAccountRequest: UpdateAccountRequest): SingleBaseDto<UserDto>
    suspend fun updateProfilePicture(userId: Int, profilePictureUrl: String): SingleBaseDto<UserDto>
}

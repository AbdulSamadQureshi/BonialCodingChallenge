package com.rabbah.domain.repository

import com.rabbah.domain.model.network.request.UpdateAccountRequest
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.UserDto
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun accountDetails(userId: Int): Flow<Request<SingleBaseDto<UserDto>>>
    fun updateAccountDetails(requestDto: UpdateAccountRequest): Flow<Request<SingleBaseDto<UserDto>>>
    fun updateProfilePicture(userId: Int, profilePictureUrl: String): Flow<Request<SingleBaseDto<UserDto>>>
}

package com.rabbah.data.repository

import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userApiService: UserApiService) : UserRepository {

    override fun accountDetails(userId: Int): Flow<Request<SingleBaseDto<UserDto>>> {
        return safeApiCall { userApiService.accountDetails(userId) }
    }

    override fun updateAccountDetails(requestDto: UpdateAccountRequest): Flow<Request<SingleBaseDto<UserDto>>> {
        return safeApiCall { userApiService.updateAccountDetails(requestDto) }
    }

    override fun updateProfilePicture(userId: Int, profilePictureUrl: String): Flow<Request<SingleBaseDto<UserDto>>> {
        return safeApiCall { userApiService.updateProfilePicture(userId, profilePictureUrl) }
    }
}

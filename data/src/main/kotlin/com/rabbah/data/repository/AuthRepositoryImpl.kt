package com.rabbah.data.repository

import com.rabbah.data.remote.service.AuthApiService
import com.rabbah.domain.model.network.request.LoginRequest
import com.rabbah.domain.model.network.request.OtpRequest
import com.rabbah.domain.model.network.response.BaseDto
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.LoginDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.UserDto
import com.rabbah.domain.model.network.response.VerifyOtpResponse
import com.rabbah.domain.repository.AuthRepository
import com.rabbah.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(private val authApiService: AuthApiService) : AuthRepository {

    override fun login(loginRequest: LoginRequest): Flow<Request<SingleBaseDto<LoginDto>>> {
        return safeApiCall { authApiService.login(loginRequest) }
    }

    override fun requestOtp(phoneNumber: String): Flow<Request<SingleBaseDto<UserDto>>> {
        return safeApiCall { authApiService.requestOtp(phoneNumber) }
    }

    override fun verifyOtp(otpRequest: OtpRequest): Flow<Request<SingleBaseDto<VerifyOtpResponse>>> {
        return safeApiCall { authApiService.verifyOtp(otpRequest) }
    }
}

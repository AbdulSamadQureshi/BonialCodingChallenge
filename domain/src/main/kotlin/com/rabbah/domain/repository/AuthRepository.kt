package com.rabbah.domain.repository

import com.rabbah.domain.model.network.request.LoginRequest
import com.rabbah.domain.model.network.request.OtpRequest
import com.rabbah.domain.model.network.response.BaseDto
import com.rabbah.domain.model.network.response.LoginDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.UserDto
import com.rabbah.domain.model.network.response.VerifyOtpResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(loginRequest: LoginRequest): Flow<Request<SingleBaseDto<LoginDto>>>
    fun requestOtp(phoneNumber: String): Flow<Request<SingleBaseDto<UserDto>>>
    fun verifyOtp(otpRequest: OtpRequest): Flow<Request<SingleBaseDto<VerifyOtpResponse>>>
}

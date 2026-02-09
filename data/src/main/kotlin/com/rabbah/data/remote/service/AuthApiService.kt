package com.rabbah.data.remote.service

import com.rabbah.domain.model.network.request.LoginRequest
import com.rabbah.domain.model.network.request.OtpRequest
import com.rabbah.domain.model.network.response.BaseDto
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.LoginDto
import com.rabbah.domain.model.network.response.UserDto
import com.rabbah.domain.model.network.response.VerifyOtpResponse

interface AuthApiService {
    suspend fun login(loginRequest: LoginRequest): SingleBaseDto<LoginDto>
    suspend fun requestOtp(phoneNumber: String): SingleBaseDto<UserDto>
    suspend fun verifyOtp(otpRequest: OtpRequest): SingleBaseDto<VerifyOtpResponse>
}

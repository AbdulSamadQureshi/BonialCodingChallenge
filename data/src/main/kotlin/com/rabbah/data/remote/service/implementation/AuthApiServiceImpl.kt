package com.rabbah.data.remote.service.implementation

import com.rabbah.data.remote.service.AuthApiService
import com.rabbah.domain.model.network.request.LoginRequest
import com.rabbah.domain.model.network.request.OtpRequest
import com.rabbah.domain.model.network.response.BaseDto
import com.rabbah.domain.model.network.response.SingleBaseDto
import com.rabbah.domain.model.network.response.LoginDto
import com.rabbah.domain.model.network.response.UserDto
import com.rabbah.domain.model.network.response.VerifyOtpResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthApiServiceImpl(private val client: HttpClient): AuthApiService {

    override suspend fun login(loginRequest: LoginRequest): SingleBaseDto<LoginDto> {
        return client.post("v1/auth/merchant-partner/login") {
            setBody(loginRequest)
        }.body()
    }

    override suspend fun requestOtp(phoneNumber: String): SingleBaseDto<UserDto> {
        return client.get("v1/auth/merchant-partner/login") {
            parameter("phoneNumber", phoneNumber)
        }.body()
    }

    override suspend fun verifyOtp(otpRequest: OtpRequest): SingleBaseDto<VerifyOtpResponse> {
        return client.post("v1/auth/merchant-partner/verify-otp") {
            setBody(otpRequest)
        }.body()
    }

}

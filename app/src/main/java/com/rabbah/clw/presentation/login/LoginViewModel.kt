package com.rabbah.clw.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.clw.presentation.utils.toErrorMessage
import com.rabbah.core.preferences.PreferenceKeys
import com.rabbah.core.preferences.SharedPrefsManager
import com.rabbah.domain.model.network.request.LoginRequest
import com.rabbah.domain.model.network.request.OtpRequest
import com.rabbah.domain.model.network.response.LoginDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.UserDto
import com.rabbah.domain.useCase.auth.LoginUseCase
import com.rabbah.domain.useCase.auth.RequestOtpUseCase
import com.rabbah.domain.useCase.auth.VerifyOtpUseCase
import com.rabbah.domain.useCase.localStorage.GetUserUseCase
import com.rabbah.domain.useCase.localStorage.LogoutUserUseCase
import com.rabbah.domain.useCase.localStorage.SaveUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase,
    private val requestOtpUseCase: RequestOtpUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val sharedPrefsManager: SharedPrefsManager
) : ViewModel() {

    private var currentPhoneNumber: String? = null

    // State for the Login screen
    private val _loginUiState = MutableStateFlow<UiState<LoginDto>>(UiState.Idle)
    val loginUiState = _loginUiState.asStateFlow()

    // State for the OTP screen's main action (verification)
    private val _otpUiState = MutableStateFlow<UiState<UserDto>>(UiState.Idle)
    val otpUiState = _otpUiState.asStateFlow()

    // State for the OTP screen's secondary action (resend)
    private val _isResendingOtp = MutableStateFlow(false)
    val isResendingOtp = _isResendingOtp.asStateFlow()

    fun login(phoneNumber: String) {
        currentPhoneNumber = phoneNumber
        viewModelScope.launch {
            loginUseCase(LoginRequest(identifier = phoneNumber)).collect { result ->
                _loginUiState.value = when (result) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        if (result.data.success) {
                            UiState.Success(result.data.data ?: LoginDto())
                        } else {
                            UiState.Error(result.data.error.toErrorMessage(result.data.message))
                        }
                    }
                    is Request.Error -> {
                        UiState.Error(result.apiError.toErrorMessage())
                    }
                }
            }
        }
    }

    fun verifyOtp(otp: String, phoneNumber: String) {
        viewModelScope.launch {
            val request = OtpRequest(identifier = phoneNumber, code = otp)
            verifyOtpUseCase(request).collect { result ->
                _otpUiState.value = when (result) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        result.data.data?.let { verifyOtpResponse ->
                            if (result.data.success) {
                                sharedPrefsManager.setValue(PreferenceKeys.KEY_ACCESS_TOKEN, verifyOtpResponse.accessToken)
                                sharedPrefsManager.setValue(PreferenceKeys.KEY_REFRESH_TOKEN, verifyOtpResponse.refreshToken)
                                verifyOtpResponse.toUserDto()?.let { userDto ->
                                    saveUserUseCase.invoke(userDto)
                                    UiState.Success(userDto)
                                } ?: UiState.Error("Failed to parse user data.")
                            } else {
                                UiState.Error(result.data.error?.toErrorMessage(result.data.message ?: "OTP verification response was empty."))
                            }
                        } ?: UiState.Error(result.data.error?.toErrorMessage(result.data.message ?: "OTP verification response was empty."))

                    }
                    is Request.Error -> {
                        UiState.Error(result.apiError.toErrorMessage())
                    }
                }
            }
        }
    }

    fun resendOtp() {
        currentPhoneNumber?.let { phone ->
            login(phone)
        }
    }

    fun logout() {
        logoutUserUseCase.invoke()
        sharedPrefsManager.removeKey(PreferenceKeys.KEY_ACCESS_TOKEN)
        sharedPrefsManager.removeKey(PreferenceKeys.KEY_REFRESH_TOKEN)
    }

    fun getLastUsedPhoneNumber(): String? {
        return getUserUseCase.invoke()?.phone
    }

    fun resetLoginState() {
        _loginUiState.value = UiState.Idle
    }

    fun resetOtpState() {
        _otpUiState.value = UiState.Idle
    }
}

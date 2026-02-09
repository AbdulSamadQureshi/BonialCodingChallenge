package com.rabbah.clw.presentation.login

import com.rabbah.domain.model.network.response.LoginDto
import com.rabbah.domain.model.network.response.SingleBaseDto

// Represents the different states of the Login UI
sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val loginResult: SingleBaseDto<LoginDto>) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

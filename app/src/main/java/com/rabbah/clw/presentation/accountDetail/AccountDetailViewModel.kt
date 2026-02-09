package com.rabbah.clw.presentation.accountDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.request.UpdateAccountRequest
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.UserDto
import com.rabbah.domain.useCase.localStorage.GetUserUseCase
import com.rabbah.domain.useCase.localStorage.SaveUserUseCase
import com.rabbah.domain.useCase.user.UpdateAccountDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccountDetailViewModel(
    private val updateAccountDetailsUseCase: UpdateAccountDetailsUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) :
    ViewModel() {
    private val _user = MutableStateFlow<UserDto?>(null)
    val user: StateFlow<UserDto?> = _user.asStateFlow()

    private val _updateAccountDetailUiState = MutableStateFlow<UiState<UserDto>>(UiState.Idle)
    val updateAccountDetailUiState: StateFlow<UiState<UserDto>> = _updateAccountDetailUiState.asStateFlow()

    fun loadAccountDetail() {
        _user.value = getUserUseCase.invoke()
    }

    fun updateAccountDetail(
        fullName: String,
        email: String,
        dateOfBirth: String,
        address: String,
        phone: String
    ) {
        viewModelScope.launch {
            val requestDto = UpdateAccountRequest(
                name = fullName,
                email = email,
                dateOfBirth = dateOfBirth,
                address = address,
                phone = phone
            )
            updateAccountDetailsUseCase.invoke(requestDto).collect { response ->
                _updateAccountDetailUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val userDto = response.data.data
                        if (userDto != null) {
                            saveUserUseCase.invoke(userDto)
                            _user.value = userDto
                            UiState.Success(userDto)
                        } else {
                            UiState.Error(response.data.message)
                        }
                    }

                    is Request.Error -> {
                        UiState.Error(
                            response.apiError?.message ?: "An unknown error occurred"
                        )
                    }
                }
            }
        }
    }
}
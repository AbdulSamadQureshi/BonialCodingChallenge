package com.rabbah.clw.presentation.transactionDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.TransactionDetailDto
import com.rabbah.domain.useCase.transaction.TransactionDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionDetailViewModel(
    private val transactionDetailUseCase: TransactionDetailUseCase
) : ViewModel() {

    private val _transactionDetailUiState = MutableStateFlow<UiState<TransactionDetailDto>>(UiState.Idle)
    val transactionDetailUiState = _transactionDetailUiState.asStateFlow()

    fun getTransactionDetail(transactionId: Int = 123, userId: Int = 123 ) {

        viewModelScope.launch {
            transactionDetailUseCase(Pair(userId, 1)).collect { response ->
                _transactionDetailUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val transactionDetail = response.data.data
                        if (transactionDetail != null) {
                            UiState.Success(transactionDetail)
                        } else {
                            UiState.Error(response.data.message ?: "An unknown error occurred")
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
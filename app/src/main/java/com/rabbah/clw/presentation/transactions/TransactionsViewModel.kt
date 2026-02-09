package com.rabbah.clw.presentation.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.clw.presentation.utils.toErrorMessage
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.TransactionDto
import com.rabbah.domain.useCase.transaction.TransactionHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionsViewModel(
    private val transactionHistoryUseCase: TransactionHistoryUseCase,
) : ViewModel() {
    private val _transactionsUiState = MutableStateFlow<UiState<List<TransactionDto>>>(UiState.Idle)
    val transactionsUiState = _transactionsUiState.asStateFlow()


    fun getTransactions(page: Int = 1) {
        viewModelScope.launch {
            transactionHistoryUseCase.invoke(Pair(page, 123)).collect { response ->
                _transactionsUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val transactions = response.data.data?.filterNotNull()
                        if (response.data.success && transactions != null) {
                            UiState.Success(transactions)
                        } else {
                             UiState.Error(response.data.error.toErrorMessage(response.data.message ?: "Transactions data is null"))
                        }
                    }

                    is Request.Error -> {
                        UiState.Error(
                            response.apiError.toErrorMessage()
                        )
                    }
                }
            }
        }
    }
}
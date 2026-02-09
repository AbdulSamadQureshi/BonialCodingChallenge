package com.rabbah.clw.presentation.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.request.TransactionsRequest
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.TransactionDto
import com.rabbah.domain.model.network.response.WalletDto
import com.rabbah.domain.useCase.transaction.TransactionHistoryUseCase
import com.rabbah.domain.useCase.wallet.ActivateCardUseCase
import com.rabbah.domain.useCase.wallet.LockCardUseCase
import com.rabbah.domain.useCase.wallet.RequestCardUseCase
import com.rabbah.domain.useCase.wallet.UnlockCardUseCase
import com.rabbah.domain.useCase.wallet.WalletUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WalletViewModel(
    private val walletUseCase: WalletUseCase,
    private val requestCardUseCase: RequestCardUseCase,
    private val lockCardUseCase: LockCardUseCase,
    private val unlockCardUseCase: UnlockCardUseCase,
    private val activateCardUseCase: ActivateCardUseCase,
    private val transactionHistoryUseCase: TransactionHistoryUseCase,
) : ViewModel() {
    private val _walletUiState = MutableStateFlow<UiState<WalletDto>>(UiState.Idle)
    val walletUiState = _walletUiState.asStateFlow()
    private val _transactionsUiState = MutableStateFlow<UiState<List<TransactionDto>>>(UiState.Idle)
    val transactionsUiState = _transactionsUiState.asStateFlow()

    fun getWallet(userId: Int = 123) {
        viewModelScope.launch {
            walletUseCase(userId).collect { response ->
                _walletUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val walletDto = response.data.data
                        if (walletDto != null) {
                            UiState.Success(walletDto)
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

    fun getTransactionsHistory(page: Int = 1) {
        viewModelScope.launch {
            val request = TransactionsRequest(page = page)
            transactionHistoryUseCase(request).collect { response ->
                _transactionsUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val transactions = response.data.data?.filterNotNull() ?: emptyList()
                        UiState.Success(transactions)
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

    fun lockCard(userId: Int, cardId: Int) {
        viewModelScope.launch {
            lockCardUseCase(userId).collect { response ->
                _walletUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val walletDto = (_walletUiState.value as? UiState.Success<WalletDto>)?.data
                        if (walletDto != null) {
                            UiState.Success(walletDto.apply { card?.isLocked = true })
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

    fun unlockCard(userId: Int, cardId: Int) {
        viewModelScope.launch {
            unlockCardUseCase(userId).collect { response ->
                _walletUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val walletDto = (_walletUiState.value as? UiState.Success<WalletDto>)?.data
                        if (walletDto != null) {
                            UiState.Success(walletDto.apply { card?.isLocked = false })
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

    fun activateCard(userId: Int, cardId: Int) {
        viewModelScope.launch {
            activateCardUseCase(userId).collect { response ->
                _walletUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val walletDto = (_walletUiState.value as? UiState.Success<WalletDto>)?.data
                        if (walletDto != null) {
                            walletDto.card?.isSuspended = false
                            UiState.Success(walletDto)
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

    fun requestCard(userId: Int) {
        viewModelScope.launch {
            requestCardUseCase(userId).collect { response ->
                _walletUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        if (response.data.data != null) {
                            UiState.Success(WalletDto(isCardRequested = true))
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

    fun topUp(walletId: Int) {

    }
}

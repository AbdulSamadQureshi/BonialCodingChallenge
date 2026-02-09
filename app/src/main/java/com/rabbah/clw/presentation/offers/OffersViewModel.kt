package com.rabbah.clw.presentation.offers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.OfferDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.useCase.offers.ActiveOffersUseCase
import com.rabbah.domain.useCase.offers.ExpiredOffersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OffersViewModel(
    private val activeOffersUseCase: ActiveOffersUseCase,
    private val expiredOffersUseCase: ExpiredOffersUseCase
) : ViewModel() {

    private val _activeOffers = MutableStateFlow<UiState<List<OfferDto>>>(UiState.Idle)
    val activeOffers = _activeOffers

    private val _expiredOffers = MutableStateFlow<UiState<List<OfferDto>>>(UiState.Idle)
    val expiredOffers = _expiredOffers

    fun getActiveOffers(userId: Int) {
        viewModelScope.launch {
            activeOffersUseCase.invoke(userId).collect { response ->
                _activeOffers.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Error -> {
                        UiState.Error(
                            response.apiError?.message ?: "An unknown error occurred"
                        )
                    }

                    is Request.Success -> {
                        val offers = response.data.data?.filterNotNull()
                        if (offers != null) {
                            UiState.Success(offers)
                        } else {
                            UiState.Error(response.data.message)
                        }

                    }
                }
            }
        }
    }

    fun getExpiredOffers(userId: Int) {
        viewModelScope.launch {
            expiredOffersUseCase.invoke(userId).collect { response ->
                _expiredOffers.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Error -> {
                        UiState.Error(
                            response.apiError?.message ?: "An unknown error occurred"
                        )
                    }

                    is Request.Success -> {
                        val offers = response.data.data?.filterNotNull()
                        if (offers != null) {
                            UiState.Success(offers)
                        } else {
                            UiState.Error(response.data.message)
                        }

                    }
                }
            }
        }
    }

}
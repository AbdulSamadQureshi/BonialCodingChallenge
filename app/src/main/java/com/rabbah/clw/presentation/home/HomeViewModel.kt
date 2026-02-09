package com.rabbah.clw.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.clw.presentation.utils.toErrorMessage
import com.rabbah.domain.model.network.response.BrochureDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.useCase.brochures.HomeOfferUseCase
import com.rabbah.domain.useCase.vend.NearbyVendRequest
import com.rabbah.domain.useCase.vend.NearbyVendsUseCase
import com.rabbah.domain.useCase.wallet.WalletUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val walletUseCase: WalletUseCase,
    private val homeOfferUseCase: HomeOfferUseCase,
    private val nearbyVendsUseCase: NearbyVendsUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _walletUiState = MutableStateFlow<UiState<WalletDto>>(UiState.Idle)
    val walletUiState = _walletUiState.asStateFlow()

    private val _offerUiState = MutableStateFlow<UiState<List<BrochureDto>>>(UiState.Idle)
    val offerUiState = _offerUiState.asStateFlow()

    private val _nearbyVendsUiState = MutableStateFlow<UiState<List<VendDto>>>(UiState.Idle)
    val nearbyVendsUiState = _nearbyVendsUiState.asStateFlow()

    private val _user = MutableStateFlow<UserDto?>(null)
    val user = _user.asStateFlow()

    init {
        loadUser()
    }

    private fun loadUser() {
        _user.value = getUserUseCase.invoke()
    }

    fun getWallet(userId: Int = 123) {
        viewModelScope.launch {
            walletUseCase(userId).collect { response ->
                _walletUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val walletDto = response.data.data
                        if (response.data.success && walletDto != null) {
                            UiState.Success(walletDto)
                        } else {
                            UiState.Error(response.data.error.toErrorMessage(response.data.message ?: "Wallet data is null"))
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


    fun getHomeOffer(userId: Int = 123) {
        viewModelScope.launch {
            homeOfferUseCase(userId).collect { response ->
                _offerUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val offers = response.data.data?.filterNotNull()
                        if (response.data.success && offers != null) {
                            UiState.Success(offers)
                        } else {
                            UiState.Error(response.data.error.toErrorMessage(response.data.message ?: "Offers response was empty."))
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

    fun getNearbyVends(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val request = NearbyVendRequest(latitude = latitude, longitude = longitude)
            nearbyVendsUseCase(request).collect { response ->
                _nearbyVendsUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val vends = response.data.data?.filterNotNull()
                        if (response.data.success && vends != null) {
                            UiState.Success(vends)
                        } else {
                            UiState.Error(response.data.error.toErrorMessage(response.data.message ?: "An error occurred"))
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

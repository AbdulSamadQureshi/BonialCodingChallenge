package com.rabbah.clw.presentation.nearbyVend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.model.network.response.VendDto
import com.rabbah.domain.useCase.vend.NearbyVendRequest
import com.rabbah.domain.useCase.vend.NearbyVendsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NearbyVendViewModel(private val nearbyVendsUseCase: NearbyVendsUseCase) : ViewModel() {

    private val _nearbyVendState = MutableStateFlow<UiState<List<VendDto>>>(UiState.Idle)
    val nearbyVendState = _nearbyVendState.asStateFlow()


    fun getNearbyVend(query: String = "", latitude: Double, longitude: Double) {

        viewModelScope.launch {
            val request = NearbyVendRequest(query = query, latitude =  latitude, longitude = longitude, page = 1)
            nearbyVendsUseCase.invoke(request).collect { response ->
                _nearbyVendState.value = when (response) {
                    is Request.Loading -> {
                        UiState.Loading
                    }
                    is Request.Error -> {
                        UiState.Error(
                            response.apiError?.message ?: "An unknown error occurred"
                        )
                    }
                    is Request.Success -> {
                        val vends = response.data.data?.filterNotNull()
                        if (vends != null) {
                           UiState.Success(vends)
                        } else {
                           UiState.Error(response.data.message ?: "An error occurred")
                        }
                    }
                }
            }
        }
    }
}

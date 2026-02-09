package com.rabbah.clw.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabbah.clw.presentation.utils.UiState
import com.rabbah.clw.presentation.utils.toErrorMessage
import com.rabbah.domain.model.network.response.BrochureDto
import com.rabbah.domain.model.network.response.Request
import com.rabbah.domain.useCase.brochures.BrochuresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BrochuresViewModel(
    private val brochuresUseCase: BrochuresUseCase,
) : ViewModel() {

    private val _brochuresUiState = MutableStateFlow<UiState<List<BrochureDto>>>(UiState.Idle)
    val brochuresUiState = _brochuresUiState.asStateFlow()

    fun getBrochures() {
        viewModelScope.launch {
            brochuresUseCase().collect { response ->
                _brochuresUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val brochures = response.data.data?.filterNotNull()
                        if (response.data.success && brochures != null) {
                            UiState.Success(brochures)
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
}

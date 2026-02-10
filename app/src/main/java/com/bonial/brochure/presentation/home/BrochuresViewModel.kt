package com.bonial.brochure.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonial.brochure.presentation.utils.UiState
import com.bonial.brochure.presentation.utils.toErrorMessage
import com.bonial.domain.model.network.response.ContentWrapperDto
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.useCase.brochures.BrochuresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BrochuresViewModel(
    private val brochuresUseCase: BrochuresUseCase,
) : ViewModel() {

    private val _brochuresUiState = MutableStateFlow<UiState<List<ContentWrapperDto>>>(UiState.Idle)
    val brochuresUiState = _brochuresUiState.asStateFlow()

    fun getBrochures() {
        viewModelScope.launch {
            brochuresUseCase().collect { response ->
                _brochuresUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val contents = response.data.embedded?.contents ?: emptyList()

                        if (contents.isNotEmpty()) {
                            UiState.Success(contents)
                        } else {
                            UiState.Error("Brochures response was empty.")
                        }
                    }

                    is Request.Error -> {
                        UiState.Error(
                            response.apiError.toErrorMessage()
                        )
                    }
                    else -> UiState.Idle
                }
            }
        }
    }
}
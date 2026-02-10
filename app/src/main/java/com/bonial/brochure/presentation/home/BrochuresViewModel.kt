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
        // Optimization: Avoid re-fetching if we already have data or are currently loading
        if (_brochuresUiState.value is UiState.Success || _brochuresUiState.value is UiState.Loading) return

        viewModelScope.launch {
            brochuresUseCase().collect { response ->
                _brochuresUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val contents = response.data.embedded?.contents ?: emptyList()
                        
                        // Filtering logic moved from UI to ViewModel for better performance
                        val filteredContents = contents.filter { wrapper ->
                            val isCorrectType = wrapper.contentType == "brochure" || wrapper.contentType == "brochurePremium"
                            val hasContent = wrapper.content.isNotEmpty()
                            val isWithinDistance = hasContent && (wrapper.content[0].distance ?: Double.MAX_VALUE) <= 5.0
                            
                            isCorrectType && isWithinDistance
                        }

                        if (filteredContents.isNotEmpty()) {
                            UiState.Success(filteredContents)
                        } else {
                            UiState.Error("No brochures found within 5km.")
                        }
                    }

                    is Request.Error -> {
                        UiState.Error(response.apiError.toErrorMessage())
                    }
                    else -> UiState.Idle
                }
            }
        }
    }
}
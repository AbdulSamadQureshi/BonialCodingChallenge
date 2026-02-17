package com.bonial.brochure.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonial.brochure.presentation.model.BrochureUi
import com.bonial.brochure.presentation.utils.UiState
import com.bonial.brochure.presentation.utils.toErrorMessage
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.useCase.brochures.BrochuresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BrochuresViewModel(
    private val brochuresUseCase: BrochuresUseCase,
) : ViewModel() {

    private val _brochuresUiState = MutableStateFlow<UiState<List<BrochureUi>>>(UiState.Idle)
    val brochuresUiState = _brochuresUiState.asStateFlow()

    init {
        getBrochures()
    }

    private fun getBrochures() {
        viewModelScope.launch {
            brochuresUseCase().collect { response ->
                _brochuresUiState.value = when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Success -> {
                        val uiModels = response.data.map { domainModel ->
                            BrochureUi(
                                id = null, // Or some ID if available in domain model
                                title = domainModel.title,
                                publisherName = domainModel.publisherName,
                                coverUrl = domainModel.coverUrl,
                                distance = domainModel.distance
                            )
                        }

                        if (uiModels.isNotEmpty()) {
                            UiState.Success(uiModels)
                        } else {
                            UiState.Error("No brochures found nearby.")
                        }
                    }

                    is Request.Error -> {
                        UiState.Error(response.apiError.toErrorMessage())
                    }
                }
            }
        }
    }
}

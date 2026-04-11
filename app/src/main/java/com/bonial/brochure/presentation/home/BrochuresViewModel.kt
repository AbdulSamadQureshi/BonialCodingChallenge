package com.bonial.brochure.presentation.home

import com.bonial.brochure.presentation.model.BrochureUi
import com.bonial.brochure.presentation.utils.toErrorMessage
import com.bonial.core.base.MviViewModel
import com.bonial.core.ui.UiState
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.useCase.brochures.BrochuresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import javax.inject.Inject

data class BrochuresState(
    val brochuresUiState: UiState<List<BrochureUi>> = UiState.Idle
)

sealed class BrochuresIntent {
    object LoadBrochures : BrochuresIntent()
}

sealed class BrochuresEffect {
    data class ShowError(val message: String) : BrochuresEffect()
}

@HiltViewModel
class BrochuresViewModel @Inject constructor(
    private val brochuresUseCase: BrochuresUseCase,
) : MviViewModel<BrochuresState, BrochuresIntent, BrochuresEffect>() {

    override fun createInitialState(): BrochuresState = BrochuresState()

    init {
        sendIntent(BrochuresIntent.LoadBrochures)
    }

    override fun handleIntent(intent: BrochuresIntent) {
        when (intent) {
            is BrochuresIntent.LoadBrochures -> getBrochures()
        }
    }

    private fun getBrochures() {
        viewModelScope.launch {
            setState { copy(brochuresUiState = UiState.Loading) }
            brochuresUseCase().collectLatest { response ->
                when (response) {
                    is Request.Loading -> {
                        setState { copy(brochuresUiState = UiState.Loading) }
                    }
                    is Request.Success -> {
                        val uiModels = response.data.map { domainModel ->
                            BrochureUi(
                                id = null,
                                title = domainModel.title,
                                publisherName = domainModel.publisherName,
                                coverUrl = domainModel.coverUrl,
                                distance = domainModel.distance
                            )
                        }

                        if (uiModels.isNotEmpty()) {
                            setState { copy(brochuresUiState = UiState.Success(uiModels)) }
                        } else {
                            setState { copy(brochuresUiState = UiState.Error("No brochures found nearby.")) }
                        }
                    }

                    is Request.Error -> {
                        val errorMessage = response.apiError.toErrorMessage()
                        setState { copy(brochuresUiState = UiState.Error(errorMessage)) }
                        setEffect { BrochuresEffect.ShowError(errorMessage) }
                    }
                }
            }
        }
    }
}

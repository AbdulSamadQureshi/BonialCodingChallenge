package com.bonial.brochure.presentation.home

import androidx.lifecycle.viewModelScope
import com.bonial.brochure.presentation.model.BrochureUi
import com.bonial.brochure.presentation.utils.toErrorMessage
import com.bonial.core.base.MviViewModel
import com.bonial.core.ui.UiState
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.useCase.brochures.BrochuresUseCase
import com.bonial.domain.useCase.favourites.GetFavouriteCoverUrlsUseCase
import com.bonial.domain.useCase.favourites.ToggleFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BrochuresState(
    val brochuresUiState: UiState<List<BrochureUi>> = UiState.Idle,
)

sealed class BrochuresIntent {
    object LoadBrochures : BrochuresIntent()
    data class ToggleFavourite(val brochure: BrochureUi) : BrochuresIntent()
}

sealed class BrochuresEffect {
    data class ShowError(val message: String) : BrochuresEffect()
}

@HiltViewModel
class BrochuresViewModel @Inject constructor(
    private val brochuresUseCase: BrochuresUseCase,
    private val getFavouriteCoverUrlsUseCase: GetFavouriteCoverUrlsUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
) : MviViewModel<BrochuresState, BrochuresIntent, BrochuresEffect>() {

    override fun createInitialState(): BrochuresState = BrochuresState()

    init {
        sendIntent(BrochuresIntent.LoadBrochures)
    }

    override fun handleIntent(intent: BrochuresIntent) {
        when (intent) {
            is BrochuresIntent.LoadBrochures -> loadBrochures()
            is BrochuresIntent.ToggleFavourite -> toggleFavourite(intent.brochure)
        }
    }

    private fun loadBrochures() {
        viewModelScope.launch {
            setState { copy(brochuresUiState = UiState.Loading) }
            combine(
                brochuresUseCase(),
                getFavouriteCoverUrlsUseCase(),
            ) { response, favouriteUrls ->
                when (response) {
                    is Request.Loading -> UiState.Loading
                    is Request.Error -> {
                        val message = response.apiError.toErrorMessage()
                        setEffect { BrochuresEffect.ShowError(message) }
                        UiState.Error(message)
                    }
                    is Request.Success -> {
                        val uiModels = response.data.map { brochure ->
                            BrochureUi(
                                id = null,
                                title = brochure.title,
                                publisherName = brochure.publisherName,
                                coverUrl = brochure.coverUrl,
                                distance = brochure.distance,
                                isFavourite = brochure.coverUrl != null && brochure.coverUrl in favouriteUrls,
                            )
                        }
                        if (uiModels.isNotEmpty()) UiState.Success(uiModels)
                        else UiState.Error("No brochures found nearby.")
                    }
                }
            }.collectLatest { uiState ->
                setState { copy(brochuresUiState = uiState) }
            }
        }
    }

    private fun toggleFavourite(brochure: BrochureUi) {
        val coverUrl = brochure.coverUrl ?: return
        viewModelScope.launch {
            toggleFavouriteUseCase(coverUrl, brochure.isFavourite)
        }
    }
}

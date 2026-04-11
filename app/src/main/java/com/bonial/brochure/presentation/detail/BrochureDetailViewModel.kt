package com.bonial.brochure.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.bonial.brochure.presentation.navigation.BrochureDetailRoute
import com.bonial.core.base.MviViewModel
import com.bonial.domain.useCase.favourites.IsFavouriteFlowUseCase
import com.bonial.domain.useCase.favourites.ToggleFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BrochureDetailState(
    val isFavourite: Boolean = false,
)

sealed class BrochureDetailIntent {
    object ToggleFavourite : BrochureDetailIntent()
}

sealed class BrochureDetailEffect

@HiltViewModel
class BrochureDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val isFavouriteFlowUseCase: IsFavouriteFlowUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
) : MviViewModel<BrochureDetailState, BrochureDetailIntent, BrochureDetailEffect>() {

    private val route = savedStateHandle.toRoute<BrochureDetailRoute>()
    private val coverUrl: String? = route.coverUrl

    override fun createInitialState(): BrochureDetailState = BrochureDetailState()

    init {
        observeFavouriteState()
    }

    override fun handleIntent(intent: BrochureDetailIntent) {
        when (intent) {
            is BrochureDetailIntent.ToggleFavourite -> toggleFavourite()
        }
    }

    private fun observeFavouriteState() {
        val url = coverUrl ?: return
        viewModelScope.launch {
            isFavouriteFlowUseCase(url).collectLatest { isFavourite ->
                setState { copy(isFavourite = isFavourite) }
            }
        }
    }

    private fun toggleFavourite() {
        val url = coverUrl ?: return
        viewModelScope.launch {
            toggleFavouriteUseCase(url, uiState.value.isFavourite)
        }
    }
}

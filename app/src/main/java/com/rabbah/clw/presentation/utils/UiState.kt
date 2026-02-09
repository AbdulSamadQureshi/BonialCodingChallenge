package com.rabbah.clw.presentation.utils

/**
 * A sealed class representing the different states of a UI component.
 * This provides type safety over a traditional data class with an enum.
 */
sealed class UiState<out T> {

    /** The UI is actively loading data. */
    object Loading : UiState<Nothing>()
    /** The UI has encountered an error. */
    data class Error(val message: String? = null) : UiState<Nothing>()
    /** The UI has successfully loaded data. */
    data class Success<out T>(val data: T) : UiState<T>()
    /** The UI is currently waiting for user action. */
    object Idle : UiState<Nothing>()
}

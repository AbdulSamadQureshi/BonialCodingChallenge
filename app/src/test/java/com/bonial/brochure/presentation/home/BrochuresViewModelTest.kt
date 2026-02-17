package com.bonial.brochure.presentation.home

import app.cash.turbine.test
import com.bonial.brochure.presentation.utils.UiState
import com.bonial.domain.model.Brochure
import com.bonial.domain.model.network.response.ApiError
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.useCase.brochures.BrochuresUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class BrochuresViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val brochuresUseCase: BrochuresUseCase = mock()
    private lateinit var viewModel: BrochuresViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `viewModel init should update UI state to Success when use case returns data`() = runTest {
        // Given
        val brochures = listOf(
            Brochure(title = "Test", coverUrl = "url", distance = 1.0, publisherName = "Pub", contentType = "brochure")
        )
        whenever(brochuresUseCase.invoke(anyOrNull())).thenReturn(flowOf(Request.Success(brochures)))

        // When
        viewModel = BrochuresViewModel(brochuresUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.brochuresUiState.test {
            val state = awaitItem()
            assertThat(state).isInstanceOf(UiState.Success::class.java)
            assertThat((state as UiState.Success).data).hasSize(1)
            assertThat(state.data[0].title).isEqualTo("Test")
        }
    }

    @Test
    fun `viewModel init should update UI state to Error when use case returns error`() = runTest {
        // Given
        whenever(brochuresUseCase.invoke(anyOrNull())).thenReturn(flowOf(Request.Error(ApiError(code = "error", message = "Error message"))))

        // When
        viewModel = BrochuresViewModel(brochuresUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.brochuresUiState.test {
            val state = awaitItem()
            assertThat(state).isInstanceOf(UiState.Error::class.java)
            assertThat((state as UiState.Error).message).isEqualTo("Error message")
        }
    }
}

package com.bonial.brochure.presentation.home

import app.cash.turbine.test
import com.bonial.brochure.presentation.utils.UiState
import com.bonial.domain.model.network.response.ApiError
import com.bonial.domain.model.network.response.BrochureDto
import com.bonial.domain.model.network.response.BrochureResponseDto
import com.bonial.domain.model.network.response.ContentWrapperDto
import com.bonial.domain.model.network.response.EmbeddedDto
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
        viewModel = BrochuresViewModel(brochuresUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getBrochures should update UI state to Success when use case returns data`() = runTest {
        // Given
        val brochure = BrochureDto(title = "Test", distance = 1.0)
        val contents = listOf(ContentWrapperDto(contentType = "brochure", content = listOf(brochure)))
        val responseDto = BrochureResponseDto(embedded = EmbeddedDto(contents = contents), page = null)
        
        whenever(brochuresUseCase.invoke(anyOrNull())).thenReturn(flowOf(Request.Success(responseDto)))

        // When
        viewModel.getBrochures()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.brochuresUiState.test {
            val state = awaitItem()
            assertThat(state).isInstanceOf(UiState.Success::class.java)
            assertThat((state as UiState.Success).data).hasSize(1)
            assertThat(state.data[0].contentType).isEqualTo("brochure")
        }
    }

    @Test
    fun `getBrochures should update UI state to Error when use case returns error`() = runTest {
        // Given
        whenever(brochuresUseCase.invoke(anyOrNull())).thenReturn(flowOf(Request.Error(ApiError(code = "error", message = "Error"))))

        // When
        viewModel.getBrochures()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.brochuresUiState.test {
            val state = awaitItem()
            assertThat(state).isInstanceOf(UiState.Error::class.java)
            assertThat((state as UiState.Error).message).isEqualTo("Error")
        }
    }
}

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
    fun `getBrochures should filter by distance and content type`() = runTest {
        // Given
        val closeBrochure1 = BrochureDto(title = "Close Brochure 1", distance = 1.0) // Keep
        val farBrochure = BrochureDto(title = "Far Brochure", distance = 6.0) // Filter out
        val closeBrochure2 = BrochureDto(title = "Close Premium Brochure", distance = 0.5) // Keep
        val boundaryBrochure = BrochureDto(title = "Boundary Brochure", distance = 5.0) // Filter out
        val nullDistanceBrochure = BrochureDto(title = "Null Distance Brochure", distance = null) // Filter out

        // This wrapper contains one brochure to keep and two to filter out
        val mixedContentWrapper = ContentWrapperDto(contentType = "brochure", content = listOf(closeBrochure1, farBrochure, nullDistanceBrochure))
        
        val premiumContentWrapper = ContentWrapperDto(contentType = "brochurePremium", content = listOf(closeBrochure2))

        // This wrapper has an unwanted content type, so its content is already empty from deserialization
        val emptyContentWrapper = ContentWrapperDto(contentType = "other_type", content = emptyList())
        
        // This wrapper only contains a far brochure, so it should be filtered out entirely after distance filtering
        val farOnlyWrapper = ContentWrapperDto(contentType = "brochure", content = listOf(boundaryBrochure))


        val contents = listOf(mixedContentWrapper, emptyContentWrapper, premiumContentWrapper, farOnlyWrapper)
        val responseDto = BrochureResponseDto(embedded = EmbeddedDto(contents = contents), page = null)

        whenever(brochuresUseCase.invoke(anyOrNull())).thenReturn(flowOf(Request.Success(responseDto)))

        // When
        viewModel.getBrochures()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.brochuresUiState.test {
            val state = awaitItem()
            assertThat(state).isInstanceOf(UiState.Success::class.java)
            val data = (state as UiState.Success).data

            // Assert that wrappers with no valid brochures are removed.
            // `mixedContentWrapper` should be present (but with filtered content).
            // `premiumContentWrapper` should be present.
            // `emptyContentWrapper` and `farOnlyWrapper` should be removed.
            assertThat(data).hasSize(2)
            
            // Get all brochures from the successful wrappers
            val allReturnedBrochures = data.flatMap { it.content }

            // Assert that the total number of brochures is correct
            assertThat(allReturnedBrochures).hasSize(2)
            
            // Assert that only close brochures are present
            assertThat(allReturnedBrochures.all { (it.distance ?: Double.MAX_VALUE) < 5.0 }).isTrue()
            
            // Assert that specific brochures are present
            assertThat(allReturnedBrochures).containsExactly(closeBrochure1, closeBrochure2)
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

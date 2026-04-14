package com.bonial.brochure.presentation.home

import app.cash.turbine.test
import com.bonial.brochure.presentation.utils.UiState
import com.bonial.domain.model.Brochure
import com.bonial.domain.model.network.response.ApiError
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.useCase.brochures.BrochuresUseCase
import com.bonial.domain.useCase.favourites.GetFavouriteCoverUrlsUseCase
import com.bonial.domain.useCase.favourites.ToggleFavouriteUseCase
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
    private val getFavouriteCoverUrlsUseCase: GetFavouriteCoverUrlsUseCase = mock()
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase = mock()
    private lateinit var viewModel: BrochuresViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        whenever(getFavouriteCoverUrlsUseCase()).thenReturn(flowOf(emptySet()))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel() = BrochuresViewModel(
        brochuresUseCase = brochuresUseCase,
        getFavouriteCoverUrlsUseCase = getFavouriteCoverUrlsUseCase,
        toggleFavouriteUseCase = toggleFavouriteUseCase,
    )

    @Test
    fun `init updates state to Success with brochures when use case returns data`() = runTest {
        // Given
        val brochures = listOf(
            Brochure(title = "Test", coverUrl = "url", distance = 1.0, publisherName = "Pub", contentType = "brochure"),
        )
        whenever(brochuresUseCase.invoke(anyOrNull())).thenReturn(flowOf(Request.Success(brochures)))

        // When
        viewModel = createViewModel()
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
    fun `init updates state to Error when use case returns error`() = runTest {
        // Given
        whenever(brochuresUseCase.invoke(anyOrNull())).thenReturn(
            flowOf(Request.Error(ApiError(code = "error", message = "Error message"))),
        )

        // When
        viewModel = createViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.brochuresUiState.test {
            val state = awaitItem()
            assertThat(state).isInstanceOf(UiState.Error::class.java)
            assertThat((state as UiState.Error).message).isEqualTo("Error message")
        }
    }

    @Test
    fun `brochure isFavourite is true when coverUrl is in favourites set`() = runTest {
        // Given
        val coverUrl = "https://example.com/cover.jpg"
        val brochures = listOf(
            Brochure(title = "Fav", coverUrl = coverUrl, distance = 1.0, publisherName = "Pub", contentType = "brochure"),
        )
        whenever(brochuresUseCase.invoke(anyOrNull())).thenReturn(flowOf(Request.Success(brochures)))
        whenever(getFavouriteCoverUrlsUseCase()).thenReturn(flowOf(setOf(coverUrl)))

        // When
        viewModel = createViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.brochuresUiState.test {
            val success = awaitItem() as UiState.Success
            assertThat(success.data.first().isFavourite).isTrue()
        }
    }
}

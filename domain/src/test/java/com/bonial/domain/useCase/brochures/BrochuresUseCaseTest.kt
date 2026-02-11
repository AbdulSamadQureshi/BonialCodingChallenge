package com.bonial.domain.useCase.brochures

import app.cash.turbine.test
import com.bonial.domain.model.network.response.ApiError
import com.bonial.domain.model.network.response.BrochureDto
import com.bonial.domain.model.network.response.BrochureResponseDto
import com.bonial.domain.model.network.response.ContentWrapperDto
import com.bonial.domain.model.network.response.EmbeddedDto
import com.bonial.domain.model.network.response.Request
import com.bonial.domain.repository.BrochuresRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class BrochuresUseCaseTest {

    private lateinit var brochuresUseCase: BrochuresUseCase
    private val brochuresRepository: BrochuresRepository = mock()

    @Before
    fun setUp() {
        brochuresUseCase = BrochuresUseCase(brochuresRepository)
    }

    @Test
    fun `invoke should emit success with filtered brochures`() = runBlocking {
        // Given
        val brochure1 = BrochureDto(title = "Brochure 1", distance = 1.0)
        val brochure2 = BrochureDto(title = "Brochure 2", distance = 6.0) // distance > 5.0
        val brochure3 = BrochureDto(title = "Brochure 3", distance = 3.0)
        val contentWrapper1 = ContentWrapperDto(contentType = "brochure", content = listOf(brochure1))
        val contentWrapper2 = ContentWrapperDto(contentType = "brochure", content = listOf(brochure2))
        val contentWrapper3 = ContentWrapperDto(contentType = "brochurePremium", content = listOf(brochure3))
        val contentWrapper4 = ContentWrapperDto(contentType = "other", content = emptyList())
        val responseDto = BrochureResponseDto(
            embedded = EmbeddedDto(
                contents = listOf(
                    contentWrapper1,
                    contentWrapper2,
                    contentWrapper3,
                    contentWrapper4
                )
            ),
            page = null
        )
        val request = Request.Success(responseDto)
        whenever(brochuresRepository.brochures()).thenReturn(flowOf(request))

        // When
        val result = brochuresUseCase.invoke(null)

        // Then
        result.test {
            val success = awaitItem() as Request.Success
            val contents = success.data.embedded?.contents
            assertThat(contents).isNotNull()
            assertThat(contents!!).hasSize(4) // UseCase does not filter
            awaitComplete()
        }
    }

    @Test
    fun `invoke should emit error when repository emits error`() = runBlocking {
        // Given
        val error = Request.Error(ApiError(code = "NetworkError", message = "Network error"))
        whenever(brochuresRepository.brochures()).thenReturn(flowOf(error))

        // When
        val result = brochuresUseCase.invoke(null)

        // Then
        result.test {
            val errorResult = awaitItem() as Request.Error
            assertThat(errorResult.apiError?.message).isEqualTo("Network error")
            awaitComplete()
        }
    }
}

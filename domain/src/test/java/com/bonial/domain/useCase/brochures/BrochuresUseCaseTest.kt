package com.bonial.domain.useCase.brochures

import app.cash.turbine.test
import com.bonial.domain.model.Brochure
import com.bonial.domain.model.network.response.ApiError
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
    fun `invoke should filter out brochures with distance greater than 5km`() = runBlocking {
        // Given
        val nearbyBrochure = Brochure(title = "Nearby", coverUrl = null, distance = 1.0, publisherName = "P1", contentType = "brochure")
        val farBrochure = Brochure(title = "Far", coverUrl = null, distance = 6.0, publisherName = "P2", contentType = "brochure")
        val request = Request.Success(listOf(nearbyBrochure, farBrochure))
        whenever(brochuresRepository.brochures()).thenReturn(flowOf(request))

        // When
        val result = brochuresUseCase.invoke(null)

        // Then
        result.test {
            val success = awaitItem() as Request.Success
            assertThat(success.data).hasSize(1)
            assertThat(success.data.first().title).isEqualTo("Nearby")
            awaitComplete()
        }
    }

    @Test
    fun `invoke should filter out brochures with unsupported content type`() = runBlocking {
        // Given
        val brochureType = Brochure(title = "B1", coverUrl = null, distance = 1.0, publisherName = "P1", contentType = "brochure")
        val premiumType = Brochure(title = "B2", coverUrl = null, distance = 1.0, publisherName = "P2", contentType = "brochurePremium")
        val otherType = Brochure(title = "B3", coverUrl = null, distance = 1.0, publisherName = "P3", contentType = "coupon")
        val request = Request.Success(listOf(brochureType, premiumType, otherType))
        whenever(brochuresRepository.brochures()).thenReturn(flowOf(request))

        // When
        val result = brochuresUseCase.invoke(null)

        // Then
        result.test {
            val success = awaitItem() as Request.Success
            assertThat(success.data).hasSize(2)
            assertThat(success.data.map { it.contentType }).containsExactly("brochure", "brochurePremium")
            awaitComplete()
        }
    }

    @Test
    fun `invoke should emit Loading when repository emits Loading`() = runBlocking {
        // Given
        whenever(brochuresRepository.brochures()).thenReturn(flowOf(Request.Loading))

        // When
        val result = brochuresUseCase.invoke(null)

        // Then
        result.test {
            assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
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

    @Test
    fun `invoke should return empty list when no brochures are eligible`() = runBlocking {
        // Given — all far away
        val brochures = listOf(
            Brochure(title = "Far1", coverUrl = null, distance = 10.0, publisherName = "P1", contentType = "brochure"),
            Brochure(title = "Far2", coverUrl = null, distance = 8.0, publisherName = "P2", contentType = "brochurePremium"),
        )
        whenever(brochuresRepository.brochures()).thenReturn(flowOf(Request.Success(brochures)))

        // When
        val result = brochuresUseCase.invoke(null)

        // Then
        result.test {
            val success = awaitItem() as Request.Success
            assertThat(success.data).isEmpty()
            awaitComplete()
        }
    }
}

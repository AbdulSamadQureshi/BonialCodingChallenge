package com.bonial.data.repository

import app.cash.turbine.test
import com.bonial.data.remote.service.BrochuresApiService
import com.bonial.domain.model.network.response.BrochureResponseDto
import com.bonial.domain.model.network.response.Request
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class BrochuresRepositoryImplTest {

    private lateinit var repository: BrochuresRepositoryImpl
    private val apiService: BrochuresApiService = mock()

    @Before
    fun setUp() {
        repository = BrochuresRepositoryImpl(apiService)
    }

    @Test
    fun `brochures should emit Loading then Success when api call is successful`() = runBlocking {
        // Given
        val mockResponse = BrochureResponseDto(embedded = null, page = null)
        whenever(apiService.brochures()).thenReturn(mockResponse)

        // When
        val flow = repository.brochures()

        // Then
        flow.test {
            assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
            val success = awaitItem() as Request.Success
            assertThat(success.data).isEqualTo(mockResponse)
            awaitComplete()
        }
    }

    @Test
    fun `brochures should emit Loading then Error when api call fails`() = runBlocking {
        // Given
        val errorMessage = "Network Error"
        whenever(apiService.brochures()).thenAnswer { throw Exception(errorMessage) }

        // When
        val flow = repository.brochures()

        // Then
        flow.test {
            assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
            val error = awaitItem() as Request.Error
            assertThat(error.apiError?.message).contains(errorMessage)
            awaitComplete()
        }
    }
}

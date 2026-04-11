package com.bonial.data.repository

import app.cash.turbine.test
import com.bonial.data.local.BrochureEntity
import com.bonial.data.local.BrochureLocalDataSource
import com.bonial.data.remote.service.BrochuresApiService
import com.bonial.domain.model.network.response.BrochureDto
import com.bonial.domain.model.network.response.BrochureResponseDto
import com.bonial.domain.model.network.response.ContentWrapperDto
import com.bonial.domain.model.network.response.EmbeddedDto
import com.bonial.domain.model.network.response.Request
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException

class BrochuresRepositoryImplTest {

    private lateinit var repository: BrochuresRepositoryImpl
    private val apiService: BrochuresApiService = mock()
    private val localDataSource: BrochureLocalDataSource = mock()

    @Before
    fun setUp() {
        repository = BrochuresRepositoryImpl(apiService, localDataSource)
    }

    @Test
    fun `brochures emits Loading then Success and caches data on network success`() = runBlocking {
        // Given
        val dto = BrochureDto(title = "Brochure 1", brochureImage = "url", distance = 1.0)
        val wrapper = ContentWrapperDto(contentType = "brochure", content = listOf(dto))
        val response = BrochureResponseDto(embedded = EmbeddedDto(contents = listOf(wrapper)), page = null)
        whenever(apiService.brochures()).thenReturn(response)

        // When / Then
        repository.brochures().test {
            assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
            val success = awaitItem() as Request.Success
            assertThat(success.data).hasSize(1)
            assertThat(success.data.first().title).isEqualTo("Brochure 1")
            awaitComplete()
        }
        verify(localDataSource).cacheBrochures(any())
    }

    @Test
    fun `brochures emits Loading then cached Success on network failure with cached data`() = runBlocking {
        // Given
        whenever(apiService.brochures()).thenThrow(IOException("No internet"))
        val cached = listOf(
            BrochureEntity(title = "Cached Brochure", coverUrl = "url", distance = 2.0, publisherName = "Publisher", contentType = "brochure"),
        )
        whenever(localDataSource.getCachedBrochures()).thenReturn(cached)

        // When / Then
        repository.brochures().test {
            assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
            val success = awaitItem() as Request.Success
            assertThat(success.data).hasSize(1)
            assertThat(success.data.first().title).isEqualTo("Cached Brochure")
            awaitComplete()
        }
    }

    @Test
    fun `brochures emits Loading then Error when network fails and cache is empty`() = runBlocking {
        // Given
        val errorMessage = "No internet"
        whenever(apiService.brochures()).thenThrow(IOException(errorMessage))
        whenever(localDataSource.getCachedBrochures()).thenReturn(emptyList())

        // When / Then
        repository.brochures().test {
            assertThat(awaitItem()).isInstanceOf(Request.Loading::class.java)
            val error = awaitItem() as Request.Error
            assertThat(error.apiError?.message).contains(errorMessage)
            awaitComplete()
        }
    }

    @Test
    fun `brochures maps DTO publisher name correctly`() = runBlocking {
        // Given
        val publisher = com.bonial.domain.model.network.response.PublisherDto(name = "Test Publisher")
        val dto = BrochureDto(title = "B1", distance = 1.0, publisher = publisher)
        val wrapper = ContentWrapperDto(contentType = "brochure", content = listOf(dto))
        val response = BrochureResponseDto(embedded = EmbeddedDto(contents = listOf(wrapper)), page = null)
        whenever(apiService.brochures()).thenReturn(response)

        // When / Then
        repository.brochures().test {
            awaitItem() // Loading
            val success = awaitItem() as Request.Success
            assertThat(success.data.first().publisherName).isEqualTo("Test Publisher")
            awaitComplete()
        }
    }
}

package com.bonial.data.local

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class BrochureLocalDataSourceImplTest {

    private val dao: BrochuresDao = mock()
    private lateinit var localDataSource: BrochureLocalDataSourceImpl

    @Before
    fun setUp() {
        localDataSource = BrochureLocalDataSourceImpl(dao)
    }

    @Test
    fun `getCachedBrochures returns entities from DAO`() = runBlocking {
        // Given
        val entities = listOf(
            BrochureEntity(title = "Brochure 1", coverUrl = "url1", distance = 1.0, publisherName = "P1", contentType = "brochure"),
            BrochureEntity(title = "Brochure 2", coverUrl = "url2", distance = 2.0, publisherName = "P2", contentType = "brochurePremium"),
        )
        whenever(dao.getAll()).thenReturn(entities)

        // When
        val result = localDataSource.getCachedBrochures()

        // Then
        assertThat(result).hasSize(2)
        assertThat(result.first().title).isEqualTo("Brochure 1")
    }

    @Test
    fun `cacheBrochures clears old data and inserts new data`() = runBlocking {
        // Given
        val entities = listOf(
            BrochureEntity(title = "New Brochure", coverUrl = "url", distance = 1.0, publisherName = "P1", contentType = "brochure"),
        )

        // When
        localDataSource.cacheBrochures(entities)

        // Then — delete-before-insert ensures cache is always fresh
        verify(dao).deleteAll()
        verify(dao).insertAll(entities)
    }

    @Test
    fun `getCachedBrochures returns empty list when cache is empty`() = runBlocking {
        // Given
        whenever(dao.getAll()).thenReturn(emptyList())

        // When
        val result = localDataSource.getCachedBrochures()

        // Then
        assertThat(result).isEmpty()
    }
}

package com.bonial.domain.utils

import com.bonial.domain.model.network.response.BrochureDto
import com.bonial.domain.model.network.response.ContentWrapperDto
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.Before
import org.junit.Test

class ContentWrapperDeserializerTest {

    private lateinit var gson: Gson

    @Before
    fun setUp() {
        gson = GsonBuilder()
            .registerTypeAdapter(ContentWrapperDto::class.java, ContentWrapperDeserializer())
            .create()
    }

    @Test
    fun `deserialize should return empty content for unwanted content types`() {
        // Given
        val json = """
            {
                "contentType": "unwanted",
                "content": {
                    "title": "Test Brochure",
                    "distance": 1.5
                }
            }
        """.trimIndent()

        // When
        val result = gson.fromJson(json, ContentWrapperDto::class.java)

        // Then
        assertThat(result.contentType).isEqualTo("unwanted")
        assertThat(result.content).isEmpty()
    }

    @Test
    fun `deserialize should handle single object content for brochure type`() {
        // Given
        val json = """
            {
                "contentType": "brochure",
                "content": {
                    "title": "Single Brochure",
                    "distance": 2.0
                }
            }
        """.trimIndent()

        // When
        val result = gson.fromJson(json, ContentWrapperDto::class.java)

        // Then
        assertThat(result.contentType).isEqualTo("brochure")
        assertThat(result.content).hasSize(1)
        assertThat(result.content[0].title).isEqualTo("Single Brochure")
        assertThat(result.content[0].distance).isEqualTo(2.0)
    }

    @Test
    fun `deserialize should handle array content for brochurePremium type`() {
        // Given
        val json = """
            {
                "contentType": "brochurePremium",
                "content": [
                    {
                        "title": "Premium 1",
                        "distance": 0.5
                    },
                    {
                        "title": "Premium 2",
                        "distance": 1.2
                    }
                ]
            }
        """.trimIndent()

        // When
        val result = gson.fromJson(json, ContentWrapperDto::class.java)

        // Then
        assertThat(result.contentType).isEqualTo("brochurePremium")
        assertThat(result.content).hasSize(2)
        assertThat(result.content[0].title).isEqualTo("Premium 1")
        assertThat(result.content[1].title).isEqualTo("Premium 2")
    }

    @Test
    fun `deserialize should return empty list when content is null`() {
        // Given
        val json = """
            {
                "contentType": "brochure",
                "content": null
            }
        """.trimIndent()

        // When
        val result = gson.fromJson(json, ContentWrapperDto::class.java)

        // Then
        assertThat(result.contentType).isEqualTo("brochure")
        assertThat(result.content).isEmpty()
    }

    @Test
    fun `deserialize should return empty list when content is missing`() {
        // Given
        val json = """
            {
                "contentType": "brochure"
            }
        """.trimIndent()

        // When
        val result = gson.fromJson(json, ContentWrapperDto::class.java)

        // Then
        assertThat(result.contentType).isEqualTo("brochure")
        assertThat(result.content).isEmpty()
    }
}

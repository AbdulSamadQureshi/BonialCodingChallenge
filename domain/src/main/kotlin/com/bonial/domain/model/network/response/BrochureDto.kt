package com.bonial.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrochureResponse(
    @SerialName("_embedded")
    val embedded: EmbeddedDto? = null
)

@Serializable
data class EmbeddedDto(
    @SerialName("contents")
    val contents: List<ContentWrapperDto> = emptyList()
)

@Serializable
data class ContentWrapperDto(
    @SerialName("contentType")
    val contentType: String? = null,
    @SerialName("content")
    val content: BrochureDto? = null
)

@Serializable
data class BrochureDto(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("brochureImage")
    val brochureImage: String? = null,
    @SerialName("distance")
    val distance: Double? = null,
    @SerialName("publisher")
    val publisher: PublisherDto? = null
)

@Serializable
data class PublisherDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String? = null
)

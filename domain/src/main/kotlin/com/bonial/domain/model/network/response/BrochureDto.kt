package com.bonial.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrochureResponse(
    @SerializedName("_embedded")
    val embedded: EmbeddedDto? = null
) : Parcelable

@Parcelize
data class EmbeddedDto(
    @SerializedName("contents")
    val contents: List<ContentWrapperDto> = emptyList()
) : Parcelable

@Parcelize
data class ContentWrapperDto(
    @SerializedName("contentType")
    val contentType: String? = null,
    @SerializedName("content")
    val content: BrochureDto? = null
) : Parcelable

@Parcelize
data class BrochureDto(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("brochureImage")
    val brochureImage: String? = null,
    @SerializedName("distance")
    val distance: Double? = null,
    @SerializedName("publisher")
    val publisher: PublisherDto? = null
) : Parcelable

@Parcelize
data class PublisherDto(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("type")
    val type: String? = null
) : Parcelable

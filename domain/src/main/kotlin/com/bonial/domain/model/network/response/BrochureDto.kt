package com.bonial.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class BrochureDto(
    @SerializedName("brochureImage")
    val brochureImage: String? = null,
    @SerializedName("distance")
    val distance: Double? = null,
    @SerializedName("publisher")
    val publisher: PublisherDto? = null,
) : Parcelable


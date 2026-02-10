package com.bonial.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class BrochureDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("brochureImage")
    val brochureImage: String,
    @SerializedName("distance")
    val distance: Double,
) : Parcelable


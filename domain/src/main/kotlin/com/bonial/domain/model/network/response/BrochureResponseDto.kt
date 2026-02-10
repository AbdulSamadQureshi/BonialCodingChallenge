package com.bonial.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrochureResponseDto(
    @SerializedName("_embedded")
    val embedded: EmbeddedDto?
): Parcelable

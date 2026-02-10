package com.bonial.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PagedDto(
    @SerializedName("number") // current page
    val number: Int = 0,
    @SerializedName("size") // page size
    val size: Int,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("totalElements")
    val totalElements: Int,
) : Parcelable

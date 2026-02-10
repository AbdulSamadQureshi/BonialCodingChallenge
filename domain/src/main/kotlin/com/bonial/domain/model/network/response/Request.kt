package com.bonial.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

sealed class Request<out T> {
    object Loading : Request<Nothing>()
    data class Error(val apiError: ApiError?) : Request<Nothing>()
    data class Success<out T>(val data: T) : Request<T>()
}

@Parcelize
data class ApiError(
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
): Parcelable
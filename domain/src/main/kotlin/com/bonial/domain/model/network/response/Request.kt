package com.bonial.domain.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

sealed class Request<out T> {
    object Loading : Request<Nothing>()
    data class Error(val apiError: ApiError?) : Request<Nothing>()
    data class Success<out T>(val data: T) : Request<T>()
}

data class ApiError(
    val code: String,
    val message: String,
)

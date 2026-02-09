package com.rabbah.utils

import com.rabbah.domain.model.network.response.ApiError
import com.rabbah.domain.model.network.response.BaseDto
import com.rabbah.domain.model.network.response.Request
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.net.HttpURLConnection

/**
 * A safeApiCall wrapper for Ktor that converts suspend functions into a Flow emitting Request states.
 * It automatically emits loading, success, and error states.
 *
 * @param apiCall The Ktor API call to be executed (e.g., apiService.getUser(id)).
 */
inline fun <reified T> safeApiCall(crossinline apiCall: suspend () -> T): Flow<Request<T>> {
    return flow {
        try {
            emit(Request.Loading)
            val result = apiCall()
            emit(Request.Success(result))
        } catch (throwable: Throwable) {
            emit(Request.Error(manageThrowable(throwable)))
        }
    }.flowOn(Dispatchers.IO)
}

/**
 * Parses a Throwable to a specific ApiError.
 */
suspend fun manageThrowable(throwable: Throwable): ApiError {
    return when (throwable) {
        is IOException -> ApiError(code = "NetworkError", message = "check your internet connection")
        is ResponseException -> {
            val response = throwable.response
            val httpCode = response.status.value
            try {
                response.body<BaseDto>().error!!
            } catch (e: Exception) {
                // If the body can't be parsed, create a new error from scratch
                ApiError(
                    message = response.status.description,
                    code = httpCode.toString(),
                )
            }
        }
        else -> {
            ApiError(code = "Unknown", message = throwable.message ?: "An unexpected error occurred")
        }
    }
}

/**
 * Provides a human-readable description for common HTTP status codes.
 */
fun getErrorDescription(code: Int): String {
    return when (code) {
        HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> "Timeout"
        HttpURLConnection.HTTP_BAD_REQUEST -> "Bad request"
        HttpURLConnection.HTTP_UNAUTHORIZED -> "Unauthorized"
        HttpURLConnection.HTTP_FORBIDDEN -> "Forbidden"
        HttpURLConnection.HTTP_NOT_FOUND -> "Not found"
        HttpURLConnection.HTTP_UNAVAILABLE -> "Service Unavailable"
        HttpURLConnection.HTTP_INTERNAL_ERROR -> "Internal server error"
        else -> "Something went wrong"
    }
}

package com.bonial.utils

import com.bonial.domain.model.network.response.ApiError
import com.bonial.domain.model.network.response.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

/**
 * A safeApiCall wrapper for Retrofit that converts suspend functions into a Flow emitting Request states.
 * It automatically emits loading, success, and error states.
 *
 * @param apiCall The Retrofit API call to be executed (e.g., apiService.getUser(id)).
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
fun manageThrowable(throwable: Throwable): ApiError {
    return when (throwable) {
        is IOException -> ApiError(code = "NetworkError", message = "check your internet connection")
        is HttpException -> {
            val response = throwable.response()
            val httpCode = response?.code() ?: 0
            // You might need a way to parse the error body if Retrofit doesn't automatically.
            // This is a placeholder for how you might handle it.
            ApiError(
                message = throwable.message(),
                code = httpCode.toString(),
            )
        }
        else -> {
            ApiError(code = "Unknown", message = throwable.message ?: "An unexpected error occurred")
        }
    }
}

package com.rabbah.domain.model.network.response

/**
 * A generic class that holds a status of API call.
 *
 *
 */
data class RequestStatusDto<out T>(val status: Status, val data: T? = null, val apiError: ApiError? = null) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
    }

    companion object {
        fun <T> success(data: T): RequestStatusDto<T> {
            return RequestStatusDto(Status.SUCCESS, data)
        }

        fun <T> error(apiError: ApiError?): RequestStatusDto<T> {
            return RequestStatusDto(Status.ERROR, null, apiError)
        }

        fun <T> loading(): RequestStatusDto<T> {
            return RequestStatusDto(Status.LOADING)
        }
    }
}

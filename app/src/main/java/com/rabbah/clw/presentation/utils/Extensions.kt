package com.rabbah.clw.presentation.utils

import com.rabbah.domain.model.network.response.ApiError

fun ApiError?.toErrorMessage(fallback: String? = "An unknown error occurred"): String {
    return this?.message ?: fallback ?: "An unknown error occurred"
}

fun ApiError?.toDetailedErrorMessage(fallback: String? = "An unknown error occurred"): String {
    val message = this?.message ?: fallback ?: "An unknown error occurred"
    val details = this?.details?.let {
        if (it.resource != null && it.identifier != null) {
            "\n${it.resource}: ${it.identifier}"
        } else {
            ""
        }
    } ?: ""
    return message + details
}


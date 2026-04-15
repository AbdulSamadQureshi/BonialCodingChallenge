package com.bonial.brochure.presentation.utils

import com.bonial.domain.model.network.response.ApiError

fun ApiError?.toErrorMessage(fallback: String? = "An unknown error occurred"): String =
    this?.message ?: fallback ?: "An unknown error occurred"

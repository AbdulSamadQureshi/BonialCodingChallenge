package com.rabbah.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    @SerialName("isActive")
    val isActive: Boolean? = false,
    @SerialName("message")
    val message: String? = ""
)

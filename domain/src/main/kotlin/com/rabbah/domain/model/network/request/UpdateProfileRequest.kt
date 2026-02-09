package com.rabbah.domain.model.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("dateOfBirth")
    val dateOfBirth: String,
    @SerialName("Address")
    val address: String
)
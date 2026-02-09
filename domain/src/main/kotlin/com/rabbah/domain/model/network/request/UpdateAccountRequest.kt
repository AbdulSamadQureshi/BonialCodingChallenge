package com.rabbah.domain.model.network.request

import kotlinx.serialization.SerialName

data class UpdateAccountRequest(
    @SerialName("name")
    val name: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("dob")
    val dateOfBirth: String? = null,
    @SerialName("address")
    val address: String? = null,
    @SerialName("phone")
    val phone: String? = null
)

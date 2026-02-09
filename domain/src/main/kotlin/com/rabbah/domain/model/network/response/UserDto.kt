package com.rabbah.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("isProfileComplete")
    val isProfileComplete: Boolean = false,
    @SerialName("profileImage")
    val profileImage: String? = null,
    @SerialName("address")
    val address: String?  = null,
    @SerialName("dateOfBirth")
    val dateOfBirth: String? = null,
    @SerialName("isActive")
    val isActive: Boolean = false,
)

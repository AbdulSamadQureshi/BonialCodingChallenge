package com.rabbah.domain.model.network.response

import com.rabbah.domain.ext.decodeBase64
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class VerifyOtpResponse(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
) {
    fun toUserDto(): UserDto? {
        val parts = accessToken.split(".")
        if (parts.size != 3) return null

        return try {
            val payloadJson = parts[1].decodeBase64()
            val json = Json { ignoreUnknownKeys = true }
            val payload = json.decodeFromString<Payload>(payloadJson)

            UserDto(
                id = payload.id,
                name = payload.name,
                phone = payload.phone,
                email = payload.email,
                isActive = payload.isActive,
                isProfileComplete = payload.isProfileComplete,
                profileImage = payload.profileImage,
                address = payload.address,
                dateOfBirth = payload.dateOfBirth,
            )

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

@Serializable
data class Payload(
    @SerialName("id")
    val id: String,
    @SerialName("email")
    val email: String? = null,
    @SerialName("phone")
    val phone: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("isProfileComplete")
    val isProfileComplete: Boolean = false,
    @SerialName("isActive")
    val isActive: Boolean = true,
    @SerialName("profileImage")
    val profileImage: String? = null,
    @SerialName("address")
    val address: String? = null,
    @SerialName("dateOfBirth")
    val dateOfBirth: String? = null,
)

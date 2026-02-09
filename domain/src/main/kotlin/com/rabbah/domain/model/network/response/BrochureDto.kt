package com.rabbah.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrochureDto(
    @SerialName("offer_id")
    val offerId: Int,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("expiry")
    val expiry: String
)

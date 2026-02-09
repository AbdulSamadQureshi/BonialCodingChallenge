package com.rabbah.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VendDto(
    @SerialName("id")
    val id: Int,

    @SerialName("latitude")
    val latitude: Double,

    @SerialName("longitude")
    val longitude: Double,

    @SerialName("title")
    val title: String,

    @SerialName("address")
    val address: String,

    @SerialName("distance")
    val distance: String,

    @SerialName("image")
    val image: String? = null,

    @SerialName("available")
    val available: Boolean,
)

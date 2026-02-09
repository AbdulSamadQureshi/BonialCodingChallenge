package com.rabbah.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("quantity")
    val quantity: Int,

    @SerialName("unit_price")
    val unitPrice: Double,

    @SerialName("image")
    val image: String?
)

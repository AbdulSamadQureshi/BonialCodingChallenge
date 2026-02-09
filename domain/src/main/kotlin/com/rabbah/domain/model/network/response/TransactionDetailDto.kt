package com.rabbah.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionDetailDto(
    @SerialName("id")
    val id: Int,

    @SerialName("grand_total")
    val grandTotal: Double,

    @SerialName("status")
    val status: Boolean,

    @SerialName("date")
    val date: String,

    @SerialName("purchased_items")
    val purchasedItems: List<ProductDto>
)


package com.rabbah.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    @SerialName("id")
    val id: Int,
    @SerialName("status")
    val status: Boolean,
    @SerialName("date")
    val date: String,
    @SerialName("grand_total")
    val grandTotal: Double,
    @SerialName("vend")
    val vendDto: VendDto,
    @SerialName("purchased_items")
    val purchasedItems: List<ProductDto>? = null
)

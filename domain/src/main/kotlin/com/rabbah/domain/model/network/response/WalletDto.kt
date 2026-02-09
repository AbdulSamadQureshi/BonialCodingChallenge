package com.rabbah.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WalletDto(

    @SerialName("balance")
    val walletBalance: Double? = null,

    @SerialName("card")
    var card: CardDto? = null,

    @SerialName("is_card_requested")
    val isCardRequested: Boolean? = false,

    @SerialName("payment_gateway")
    val paymentGateway: String? = null
)

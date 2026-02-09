package com.rabbah.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardDto(
    @SerialName("type")
    val type: String,

    @SerialName("owner_name")
    val ownerName: String,

    @SerialName("expiry_date")
    val expiryDate: String,

    @SerialName("number")
    val number: String,

    @SerialName("cvv")
    val cvv: String,

    @SerialName("is_locked")
    var isLocked: Boolean? = false,

    @SerialName("is_suspended")
    var isSuspended: Boolean? = false,
)

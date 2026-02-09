package com.rabbah.domain.model.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionsRequest(
    @SerialName("page")
    val page: Int,
    @SerialName("limit")
    val limit: Int = 10,
)


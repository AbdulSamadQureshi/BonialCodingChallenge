package com.rabbah.domain.model.network.request

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class OtpRequest(
    @SerialName("identifier")
    val identifier: String,
    @SerialName("code")
    val code: String,
    @EncodeDefault
    @SerialName("purpose")
    val purpose: String = "login"
)

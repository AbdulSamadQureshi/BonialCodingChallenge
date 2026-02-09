package com.rabbah.domain.model.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppVersionDto(
    @SerialName("version_name")
    val versionName: String,
    @SerialName("version_code")
    val versionCode: Double,
    @SerialName("is_forced")
    val isForced:Boolean
)

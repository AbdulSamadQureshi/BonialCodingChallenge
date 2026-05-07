package com.bonial.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonItemDto(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = "",
)

package com.bonial.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonListResponseDto(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val results: List<PokemonItemDto>? = null,
)

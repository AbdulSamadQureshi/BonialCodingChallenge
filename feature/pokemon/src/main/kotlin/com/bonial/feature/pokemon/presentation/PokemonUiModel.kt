package com.bonial.feature.pokemon.presentation

import com.bonial.domain.model.Pokemon

data class PokemonUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
)

fun Pokemon.toUiModel(): PokemonUiModel =
    PokemonUiModel(id = id, name = name, imageUrl = imageUrl)

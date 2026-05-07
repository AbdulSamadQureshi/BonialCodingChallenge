package com.bonial.data.mapper

import com.bonial.data.remote.model.PokemonItemDto
import com.bonial.data.remote.model.PokemonListResponseDto
import com.bonial.domain.model.Pokemon
import com.bonial.domain.repository.PokemonPage

private const val SPRITE_BASE_URL =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"

internal fun PokemonItemDto.toDomain(): Pokemon {
    // PokéAPI list entries contain no ID field — extract it from the trailing path segment
    // of the resource URL, e.g. "https://pokeapi.co/api/v2/pokemon/1/" → 1.
    val id = url.trimEnd('/').substringAfterLast('/').toIntOrNull() ?: 0
    return Pokemon(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        imageUrl = "$SPRITE_BASE_URL$id.png",
    )
}

internal fun PokemonListResponseDto.toDomainPage(): PokemonPage =
    PokemonPage(
        pokemon = results.orEmpty().map { it.toDomain() },
        totalCount = count,
        hasNextPage = next != null,
    )

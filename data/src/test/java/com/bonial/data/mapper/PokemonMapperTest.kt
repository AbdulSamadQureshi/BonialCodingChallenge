package com.bonial.data.mapper

import com.bonial.data.remote.model.PokemonItemDto
import com.bonial.data.remote.model.PokemonListResponseDto
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PokemonMapperTest {
    @Test
    fun `pokemon item url with trailing slash yields correct numeric id`() {
        val dto = PokemonItemDto(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/")

        val domain = dto.toDomain()

        assertThat(domain.id).isEqualTo(1)
    }

    @Test
    fun `pokemon name is capitalised when mapped to domain`() {
        val dto = PokemonItemDto(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/")

        val domain = dto.toDomain()

        assertThat(domain.name).isEqualTo("Bulbasaur")
    }

    @Test
    fun `sprite url is constructed from the extracted pokemon id`() {
        val dto = PokemonItemDto(name = "charmander", url = "https://pokeapi.co/api/v2/pokemon/4/")

        val domain = dto.toDomain()

        assertThat(domain.imageUrl).isEqualTo(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
        )
    }

    @Test
    fun `malformed url without numeric segment produces id zero`() {
        val dto = PokemonItemDto(name = "unknown", url = "https://pokeapi.co/api/v2/pokemon/abc/")

        val domain = dto.toDomain()

        assertThat(domain.id).isEqualTo(0)
    }

    @Test
    fun `response with next url is mapped to a page that has a next page`() {
        val response = PokemonListResponseDto(
            count = 1302,
            next = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
            previous = null,
            results = listOf(PokemonItemDto(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/")),
        )

        val page = response.toDomainPage()

        assertThat(page.hasNextPage).isTrue()
        assertThat(page.totalCount).isEqualTo(1302)
    }

    @Test
    fun `response with null next url is mapped to a page that has no next page`() {
        val response = PokemonListResponseDto(
            count = 5,
            next = null,
            previous = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=5",
            results = listOf(PokemonItemDto(name = "mew", url = "https://pokeapi.co/api/v2/pokemon/151/")),
        )

        val page = response.toDomainPage()

        assertThat(page.hasNextPage).isFalse()
    }

    @Test
    fun `response with null results list maps to an empty pokemon list`() {
        val response = PokemonListResponseDto(count = 0, next = null, previous = null, results = null)

        val page = response.toDomainPage()

        assertThat(page.pokemon).isEmpty()
    }

    @Test
    fun `all items in results are mapped to domain pokemon`() {
        val response = PokemonListResponseDto(
            count = 3,
            next = null,
            previous = null,
            results = listOf(
                PokemonItemDto("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
                PokemonItemDto("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
                PokemonItemDto("venusaur", "https://pokeapi.co/api/v2/pokemon/3/"),
            ),
        )

        val page = response.toDomainPage()

        assertThat(page.pokemon).hasSize(3)
        assertThat(page.pokemon.map { it.id }).containsExactly(1, 2, 3)
        assertThat(page.pokemon.map { it.name }).containsExactly("Bulbasaur", "Ivysaur", "Venusaur")
    }
}

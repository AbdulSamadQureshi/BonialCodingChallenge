package com.bonial.brochure.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.weight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.bonial.brochure.presentation.detail.CharacterDetailScreen
import com.bonial.brochure.presentation.home.CharacterDetailViewModel
import com.bonial.brochure.presentation.home.CharactersScreen
import com.bonial.brochure.presentation.home.CharactersViewModel
import com.bonial.feature.pokemon.navigation.PokemonListKey
import com.bonial.feature.pokemon.presentation.PokemonListScreen
import com.bonial.feature.pokemon.presentation.PokemonListViewModel

@Composable
fun CharacterNavGraph() {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                0 -> CharactersTab()
                1 -> PokemonTab()
            }
        }

        NavigationBar {
            NavigationBarItem(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                icon = { Icon(Icons.Default.People, contentDescription = null) },
                label = { Text("Characters") },
            )
            NavigationBarItem(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                icon = { Icon(Icons.Default.CatchingPokemon, contentDescription = null) },
                label = { Text("Pokémon") },
            )
        }
    }
}

@Composable
private fun CharactersTab() {
    val backStack = rememberNavBackStack(CharacterListKey)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators =
            listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        entryProvider =
            entryProvider {
                entry<CharacterListKey> {
                    val viewModel: CharactersViewModel = hiltViewModel()
                    CharactersScreen(
                        viewModel = viewModel,
                        onCharacterClick = { characterId ->
                            backStack.add(CharacterDetailKey(id = characterId))
                        },
                    )
                }

                entry<CharacterDetailKey> { key ->
                    val viewModel =
                        hiltViewModel<CharacterDetailViewModel, CharacterDetailViewModel.Factory>(
                            creationCallback = { factory -> factory.create(key) },
                        )
                    CharacterDetailScreen(
                        viewModel = viewModel,
                        onBack = { backStack.removeLastOrNull() },
                    )
                }
            },
    )
}

@Composable
private fun PokemonTab() {
    val backStack = rememberNavBackStack(PokemonListKey)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators =
            listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        entryProvider =
            entryProvider {
                entry<PokemonListKey> {
                    val viewModel: PokemonListViewModel = hiltViewModel()
                    PokemonListScreen(viewModel = viewModel)
                }
            },
    )
}

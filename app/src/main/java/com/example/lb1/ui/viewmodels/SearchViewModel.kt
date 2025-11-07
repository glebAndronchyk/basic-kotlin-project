package com.example.lb1.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class GameDto(
    val name: String,
    val description: String
)

val mockGames = listOf(
    GameDto(
        name = "Cyber Warriors 2077",
        description = "An immersive open-world RPG set in a dystopian future where technology and humanity collide. Navigate through neon-lit streets and make choices that shape your destiny."
    ),
    GameDto(
        name = "Fantasy Quest: Legends",
        description = "Embark on an epic adventure through mystical lands filled with dragons, magic, and ancient treasures. Gather your party and save the kingdom from darkness."
    ),
    GameDto(
        name = "Zombie Survival Protocol",
        description = "Fight for survival in a post-apocalyptic world overrun by the undead. Scavenge for resources, build shelter, and form alliances to stay alive."
    ),
    GameDto(
        name = "Speed Racers Ultimate",
        description = "Experience high-octane racing action with stunning graphics and realistic physics. Compete in global tournaments and customize your dream car."
    ),
    GameDto(
        name = "Puzzle Master Deluxe",
        description = "Challenge your mind with hundreds of brain-teasing puzzles. From logic riddles to spatial challenges, each level will test your problem-solving skills."
    )
)

class SearchViewModel : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow(mockGames)
    val searchResults: StateFlow<List<GameDto>> = _searchResults.asStateFlow()

    fun changeSearchQuery(query: String) {
        _searchQuery.value = query;
        performSearch();
    }

    fun performSearch() {
        _searchResults.value = if (_searchQuery.value.isEmpty()) {
            mockGames
        } else {
            mockGames.filter { (name, description) -> name.contains(_searchQuery.value, ignoreCase = true) }
        }
    }
}
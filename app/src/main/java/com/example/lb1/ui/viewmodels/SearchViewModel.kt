package com.example.lb1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lb1.data.database.DbProvider
import com.example.lb1.data.repositories.GameRepository
import com.example.lb1.domain.resources.HttpGamesResource
import com.example.lb1.domain.resources.ListOfGamesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.emptyList

data class GameDto(
    val id: String,
    val name: String,
    val description: String
)

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val gamesRepository = GameRepository(DbProvider.getDatabase(application))
    private val gamesResource: HttpGamesResource = HttpGamesResource;

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<GameDto>>(emptyList())
    val searchResults: StateFlow<List<GameDto>> = _searchResults.asStateFlow()

    init {
        fetchData()
    }

    fun changeSearchQuery(query: String) {
        _searchQuery.value = query;
        performSearch();
    }

    fun performSearch() {
        _searchResults.value = if (_searchQuery.value.isEmpty()) {
            _searchResults.value
        } else {
            _searchResults.value.filter { (name, description) -> name.contains(_searchQuery.value, ignoreCase = true) }
        }
    }

    fun saveUniqueToDb() {
        viewModelScope.launch {
            gamesRepository.insertUniqueOnly(searchResults.value.map { value -> com.example.lb1.domain.model.GameDto(
                id = value.id,
                name = value.name,
                description = value.description,
            ) })
        }
    }

    private fun fetchData() {
        gamesResource.listAllGames().enqueue(object : Callback<List<ListOfGamesResponse>> {
            override fun onFailure(call: Call<List<ListOfGamesResponse>>, t: Throwable) {
                _searchResults.value = emptyList()
            }

            override fun onResponse(
                call: Call<List<ListOfGamesResponse>>,
                response: Response<List<ListOfGamesResponse>>
            ) {
                val games = response.body()

                _searchResults.value = games?.map { game ->
                    GameDto(
                        id = game.id,
                        name = game.name,
                        description = game.description
                    )
                } ?: emptyList()
            }
        })
    }
}
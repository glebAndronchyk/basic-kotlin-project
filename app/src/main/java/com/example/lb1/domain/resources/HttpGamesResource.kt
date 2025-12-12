package com.example.lb1.domain.resources

import com.example.lb1.data.http.HttpClient
import retrofit2.Call
import retrofit2.http.GET

data class ListOfGamesResponse(
    val id: String,
    val name: String,
    val description: String,
)

interface GamesResource {
    @GET("/games")
    fun listAllGames(): Call<List<ListOfGamesResponse>>
}

object HttpGamesResource {
    private val resource: GamesResource by lazy {
        HttpClient.getInstance().create<GamesResource>(GamesResource::class.java);
    }

    fun listAllGames(): Call<List<ListOfGamesResponse>> {
        return resource.listAllGames()
    }
}
package com.example.lb1.data.repositories

import com.example.lb1.data.database.AppDatabase
import com.example.lb1.domain.model.Clip
import com.example.lb1.domain.model.ClipWithRelations
import com.example.lb1.domain.model.CreateClipDto
import com.example.lb1.domain.model.Game
import com.example.lb1.domain.model.GameDto

class GameRepository(
    private val database: AppDatabase
) {
    suspend fun insertUniqueOnly(games: List<GameDto>) {
        database.gameDao().insertUniqueOnly(games.map { (id, name, description) -> Game(
            id = id,
            name = name,
            description = description,
        ) })
    }
}
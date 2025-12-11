package com.example.lb1.data.repositories

import com.example.lb1.data.database.AppDatabase
import com.example.lb1.domain.model.Creator

class CreatorRepository(
    private val database: AppDatabase
) {
    fun getAllCreators(): List<Creator> {
        return database.creatorDao().getAll()
    }

    suspend fun insertCreator(creator: Creator): Long {
        return database.creatorDao().insert(creator)
    }

    suspend fun updateCreator(creator: Creator) {
        database.creatorDao().update(creator)
    }

    suspend fun deleteCreator(creator: Creator) {
        database.creatorDao().delete(creator)
    }
}

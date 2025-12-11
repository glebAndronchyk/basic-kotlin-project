package com.example.lb1.data.repositories

import com.example.lb1.data.database.AppDatabase
import com.example.lb1.domain.model.Narrator

class NarratorRepository(
    private val database: AppDatabase
) {
    fun getAllNarrators(): List<Narrator> {
        return database.narratorDao().getAll()
    }

    suspend fun insertNarrator(narrator: Narrator): Long {
        return database.narratorDao().insert(narrator)
    }

    suspend fun updateNarrator(narrator: Narrator) {
        database.narratorDao().update(narrator)
    }

    suspend fun deleteNarrator(narrator: Narrator) {
        database.narratorDao().delete(narrator)
    }
}
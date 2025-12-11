package com.example.lb1.data.repositories

import com.example.lb1.data.database.AppDatabase
import com.example.lb1.domain.model.CreateNarratorDto
import com.example.lb1.domain.model.Narrator

class NarratorRepository(
    private val database: AppDatabase
) {
    fun getAllNarrators(): List<Narrator> {
        return database.narratorDao().getAll()
    }

    suspend fun insertNarrator(dto: CreateNarratorDto): Long {
        val narrator = Narrator(
            id = 0,
            avatar = dto.avatar,
            name = dto.name
        )
        return database.narratorDao().insert(narrator)
    }

    suspend fun updateNarrator(narrator: Narrator) {
        database.narratorDao().update(narrator)
    }

    suspend fun deleteNarrator(narrator: Narrator) {
        database.narratorDao().delete(narrator)
    }
}
package com.example.lb1.data.repositories

import com.example.lb1.data.database.AppDatabase
import com.example.lb1.domain.model.Clip
import com.example.lb1.domain.model.ClipWithRelations

class ClipRepository(
    private val database: AppDatabase
) {
    fun getAllClipsWithRelations(): List<ClipWithRelations> {
        return database.clipsDao().getAllWithRelations()
    }

    suspend fun insertClip(clip: Clip): Long {
        return database.clipsDao().insert(clip)
    }

    suspend fun updateClip(clip: Clip) {
        database.clipsDao().update(clip)
    }

    suspend fun deleteClip(clip: Clip) {
        database.clipsDao().delete(clip)
    }
}
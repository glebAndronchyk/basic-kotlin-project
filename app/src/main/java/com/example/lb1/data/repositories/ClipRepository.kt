package com.example.lb1.data.repositories

import com.example.lb1.data.database.AppDatabase
import com.example.lb1.domain.model.Clip
import com.example.lb1.domain.model.ClipWithRelations
import com.example.lb1.domain.model.CreateClipDto

class ClipRepository(
    private val database: AppDatabase
) {
    fun getAllClipsWithRelations(): List<ClipWithRelations> {
        return database.clipsDao().getAllWithRelations()
    }

    suspend fun insertClip(dto: CreateClipDto): Long {
        val clip = Clip(
            id = 0,
            previewUrl = dto.previewUrl,
            name = dto.name,
            description = dto.description,
            duration = dto.duration,
            creatorId = dto.creatorId,
            narratorId = dto.narratorId
        )
        return database.clipsDao().insert(clip)
    }

    suspend fun updateClip(clip: Clip) {
        database.clipsDao().update(clip)
    }

    suspend fun deleteClip(clip: Clip) {
        database.clipsDao().delete(clip)
    }
}
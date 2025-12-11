package com.example.lb1.data.repositories

import com.example.lb1.data.database.AppDatabase
import com.example.lb1.domain.model.Stream
import com.example.lb1.domain.model.StreamWithRelations

class StreamRepository(
    private val database: AppDatabase
) {
    fun getAllStreamsWithRelations(): List<StreamWithRelations> {
        return database.streamDao().getAllWithRelations()
    }

    suspend fun insertStream(stream: Stream): Long {
        return database.streamDao().insert(stream)
    }

    suspend fun updateStream(stream: Stream) {
        database.streamDao().update(stream)
    }

    suspend fun deleteStream(stream: Stream) {
        database.streamDao().delete(stream)
    }
}
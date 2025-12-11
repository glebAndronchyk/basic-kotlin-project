package com.example.lb1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.lb1.data.database.DbProvider
import com.example.lb1.data.repositories.ClipRepository
import com.example.lb1.data.repositories.NarratorRepository
import com.example.lb1.data.repositories.StreamRepository
import com.example.lb1.domain.model.CreateNarratorDto
import com.example.lb1.domain.model.CreateStreamDto
import com.example.lb1.domain.model.Narrator

class WatchConfigureViewModel(application: Application) : AndroidViewModel(application) {
    private val streamRepository = StreamRepository(DbProvider.getDatabase(application.applicationContext))
    private val narratorRepository = NarratorRepository(DbProvider.getDatabase(application.applicationContext))
    private val clipRepository = ClipRepository(DbProvider.getDatabase(application.applicationContext))

    suspend fun createStream(dto: CreateStreamDto) {
        streamRepository.insertStream(dto)
    }

    suspend fun createNarrator(dto: CreateNarratorDto) {
        narratorRepository.insertNarrator(dto)
    }

    suspend fun updateNarrator(narrator: Narrator) {
        narratorRepository.updateNarrator(narrator)
    }

    suspend fun deleteNarrator(narrator: Narrator) {
        narratorRepository.deleteNarrator(narrator)
    }
}
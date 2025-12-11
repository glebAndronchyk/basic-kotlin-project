package com.example.lb1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lb1.data.database.DbProvider
import com.example.lb1.data.repositories.ClipRepository
import com.example.lb1.ui.presentations.StreamPresentation
import com.example.lb1.data.repositories.StreamRepository
import com.example.lb1.data.repositories.NarratorRepository
import com.example.lb1.ui.presentations.NarratorPresentation
import com.example.lb1.ui.presentations.ClipPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StreamsViewModel(application: Application) : AndroidViewModel(application) {
    private val streamRepository = StreamRepository(DbProvider.getDatabase(application.applicationContext))
    private val narratorRepository = NarratorRepository(DbProvider.getDatabase(application.applicationContext))
    private val clipRepository = ClipRepository(DbProvider.getDatabase(application.applicationContext))

    private val _streams = MutableStateFlow<List<StreamPresentation>>(emptyList())
    val streams: StateFlow<List<StreamPresentation>> = _streams.asStateFlow()

    private val _narrators = MutableStateFlow<List<NarratorPresentation>>(emptyList())
    val narrators: StateFlow<List<NarratorPresentation>> = _narrators.asStateFlow()

    private val _clips = MutableStateFlow<List<ClipPresentation>>(emptyList())
    val clips: StateFlow<List<ClipPresentation>> = _clips.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadStreams()
            loadClips()
            loadNarrators()
        }
    }

    suspend fun loadStreams() {
        val streamDaos = streamRepository.getAllStreamsWithRelations()
        _streams.value = streamDaos.map { dao ->
            StreamPresentation(
                previewUrl = dao.stream.previewUrl,
                name = dao.stream.name,
                description = dao.stream.description,
                id = dao.stream.id,
                narratorName = dao.narrator.name,
                narratorId = dao.narrator.id
            )
        }
    }

    suspend fun loadNarrators() {
        val narratorsDaos = narratorRepository.getAllNarrators()
        _narrators.value = narratorsDaos.map { dao ->
            NarratorPresentation(
                avatar = dao.avatar,
                name = dao.name,
                id = dao.id
            )
        }
    }

    suspend fun loadClips() {
        val clipDaos = clipRepository.getAllClipsWithRelations()
        _clips.value = clipDaos.map { dao ->
            ClipPresentation(
                previewUrl = dao.clip.previewUrl,
                name = dao.clip.name,
                id = dao.clip.id,
                duration = dao.clip.duration,
                description = dao.clip.description,
                narratorName = dao.narrator.name,
                creatorName = dao.creator.name,
                creatorId = dao.creator.id,
                narratorId = dao.narrator.id
            )
        }
    }
}

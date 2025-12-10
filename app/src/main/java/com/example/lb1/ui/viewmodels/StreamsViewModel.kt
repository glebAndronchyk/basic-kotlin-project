package com.example.lb1.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.lb1.repositories.ClipRepository
import com.example.lb1.ui.presentations.StreamPresentation
import com.example.lb1.repositories.StreamRepository
import com.example.lb1.repositories.NarratorRepository
import com.example.lb1.ui.presentations.NarratorPresentation
import com.example.lb1.ui.presentations.ClipPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StreamsViewModel : ViewModel() {
    private val streamRepository = StreamRepository()
    private val narratorRepository = NarratorRepository()
    private val clipRepository = ClipRepository()

    private val _streams = MutableStateFlow<List<StreamPresentation>>(emptyList())
    val streams: StateFlow<List<StreamPresentation>> = _streams.asStateFlow()

    private val _narrators = MutableStateFlow<List<NarratorPresentation>>(emptyList())
    val narrators: StateFlow<List<NarratorPresentation>> = _narrators.asStateFlow()

    private val _clips = MutableStateFlow<List<ClipPresentation>>(emptyList())
    val clips: StateFlow<List<ClipPresentation>> = _clips.asStateFlow()

    init {
        loadStreams()
        loadClips()
        loadNarrators()
    }

    fun loadStreams() {
        val streamDaos = streamRepository.listAllStreams()
        _streams.value = streamDaos.map { dao ->
            StreamPresentation(
                previewUrl = dao.previewUrl,
                name = dao.name,
                description = dao.description,
                id = dao.id,
                narratorName = dao.narratorName,
                narratorId = dao.narratorId
            )
        }
    }

    fun loadNarrators() {
        val narratorsDaos = narratorRepository.getAllNarrators()
        _narrators.value = narratorsDaos.map { dao ->
            NarratorPresentation(
                avatar = dao.avatar,
                name = dao.name,
                id = dao.id
            )
        }
    }

    fun loadClips() {
        val clipDaos = clipRepository.getAllClips()
        _clips.value = clipDaos.map { dao ->
            ClipPresentation(
                previewUrl = dao.previewUrl,
                name = dao.name,
                narratorName = dao.narratorName,
                creatorName = dao.creatorName,
                description = dao.description,
                duration = dao.duration,
                id = dao.id,
                creatorId = dao.creatorId,
                narratorId = dao.narratorId
            )
        }
    }
}

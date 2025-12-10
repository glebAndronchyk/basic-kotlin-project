package com.example.lb1.ui.presentations

data class StreamPresentation(
    val previewUrl: String,
    val name: String,
    val description: String,
    val id: Number,
    val narratorName: String,
    val narratorId: String,
)

data class ClipPresentation(
    val previewUrl: String,
    val name: String,
    val narratorName: String,
    val creatorName: String,
    val description: String,
    val duration: String,
    val id: Number,
    val creatorId: Number,
    val narratorId: Number,
)

data class NarratorPresentation(
    val avatar: String,
    val name: String,
    val id: Number,
)

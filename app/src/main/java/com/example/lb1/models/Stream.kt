package com.example.lb1.models

data class StreamDao(
    val previewUrl: String,
    val name: String,
    val description: String,
    val id: Number,
    //
    val narratorName: String,
    //
    val narratorId: String,
)

data class ClipDao(
    val previewUrl: String,
    val name: String,
    //
    val narratorName: String,
    val creatorName: String,
    //
    val description: String,
    val duration: String,
    val id: Number,
    val creatorId: Number,
    val narratorId: Number,
)

data class NarratorDao(
    val avatar: String,
    val name: String,
    val id: Number,
)

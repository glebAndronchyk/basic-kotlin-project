package com.example.lb1.domain.model

data class CreateStreamDto(
    val narratorId: String,
    val previewUrl: String,
    val name: String,
    val description: String,
)

data class CreateClipDto(
    val previewUrl: String,
    val name: String,
    val description: String,
    val duration: String,
    val creatorId: Long,
    val narratorId: Long,
)

data class CreateNarratorDto(
    val avatar: String,
    val name: String,
)

data class UpdateNarratorDto(
    val id: Long,
    val avatar: String,
    val name: String,
)

data class CreateCreatorDto(
    val avatar: String,
    val name: String,
)

package com.example.lb1.repositories

import com.example.lb1.data.MockData
import com.example.lb1.models.ClipDao

class ClipRepository {
    fun getAllClips(): List<ClipDao> {
        return MockData.mockClips
    }
}
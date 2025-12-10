package com.example.lb1.repositories

import com.example.lb1.data.MockData
import com.example.lb1.models.StreamDao

class StreamRepository {
    fun listAllStreams(): List<StreamDao> {
        return MockData.mockStreams
    }
}
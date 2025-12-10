package com.example.lb1.repositories

import com.example.lb1.data.MockData
import com.example.lb1.models.NarratorDao

class NarratorRepository {
    fun getAllNarrators(): List<NarratorDao> {
        return MockData.mockNarrators
    }
}
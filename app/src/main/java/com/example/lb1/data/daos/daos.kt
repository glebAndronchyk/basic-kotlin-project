package com.example.lb1.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.example.lb1.domain.model.Clip
import com.example.lb1.domain.model.ClipWithRelations
import com.example.lb1.domain.model.Creator
import com.example.lb1.domain.model.Game
import com.example.lb1.domain.model.Narrator
import com.example.lb1.domain.model.Stream
import com.example.lb1.domain.model.StreamWithRelations

@Dao
interface StreamDao {
    @Query("select * from streams")
    fun getAllWithRelations(): List<StreamWithRelations>
    @Insert
    suspend fun insert(stream: Stream): Long
    @Update
    suspend fun update(stream: Stream)
    @Delete
    suspend fun delete(stream: Stream)
}

@Dao
interface ClipsDao {
    @Query("select * from clips")
    fun getAllWithRelations(): List<ClipWithRelations>
    @Insert
    suspend fun insert(clip: Clip): Long
    @Update
    suspend fun update(clip: Clip)
    @Delete
    suspend fun delete(clip: Clip)
}

@Dao
interface NarratorDao {
    @Query("select * from narrators")
    fun getAll(): List<Narrator>
    @Insert
    suspend fun insert(narrator: Narrator): Long
    @Update
    suspend fun update(narrator: Narrator)
    @Delete
    suspend fun delete(narrator: Narrator)
}

@Dao
interface CreatorDao {
    @Query("select * from creators")
    fun getAll(): List<Creator>
    @Insert
    suspend fun insert(creator: Creator): Long
    @Update
    suspend fun update(creator: Creator)
    @Delete
    suspend fun delete(creator: Creator)
}

@Dao
interface GameDao {
    @Insert(onConflict = IGNORE)
    suspend fun insertUniqueOnly(games: List<Game>): List<Long>
}


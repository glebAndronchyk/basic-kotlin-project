package com.example.lb1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lb1.data.daos.ClipsDao
import com.example.lb1.data.daos.CreatorDao
import com.example.lb1.data.daos.NarratorDao
import com.example.lb1.data.daos.StreamDao
import com.example.lb1.domain.model.Stream
import com.example.lb1.domain.model.Creator
import com.example.lb1.domain.model.Narrator
import com.example.lb1.domain.model.Clip

@Database(entities = [
    Stream::class,
    Creator::class,
    Narrator::class,
    Clip::class,
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun streamDao(): StreamDao
    abstract fun creatorDao(): CreatorDao
    abstract fun narratorDao(): NarratorDao
    abstract fun clipsDao(): ClipsDao
}

object DbProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}

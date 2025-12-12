package com.example.lb1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lb1.data.daos.ClipsDao
import com.example.lb1.data.daos.CreatorDao
import com.example.lb1.data.daos.GameDao
import com.example.lb1.data.daos.NarratorDao
import com.example.lb1.data.daos.StreamDao
import com.example.lb1.domain.model.Stream
import com.example.lb1.domain.model.Creator
import com.example.lb1.domain.model.Narrator
import com.example.lb1.domain.model.Clip
import com.example.lb1.domain.model.Game

@Database(entities = [
    Stream::class,
    Creator::class,
    Narrator::class,
    Clip::class,
    Game::class,
], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun streamDao(): StreamDao
    abstract fun creatorDao(): CreatorDao
    abstract fun narratorDao(): NarratorDao
    abstract fun clipsDao(): ClipsDao
    abstract fun gameDao(): GameDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS games (
                id TEXT PRIMARY KEY NOT NULL,
                description TEXT NOT NULL,
                name TEXT NOT NULL
            )
            """.trimIndent()
        )
    }
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
            )
                .addMigrations(MIGRATION_1_2)
                .build()
            INSTANCE = instance
            instance
        }
    }
}

package com.example.lb1.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.ForeignKey.Companion.NO_ACTION
import androidx.room.PrimaryKey
import androidx.room.Relation

data class StreamWithRelations(
    @Embedded val stream: Stream,
    @Relation(
        parentColumn = "narrator_id",
        entityColumn = "id"
    )
    val narrator: Narrator
)

data class ClipWithRelations(
    @Embedded val clip: Clip,
    @Relation(
        parentColumn = "creator_id",
        entityColumn = "id"
    )
    val creator: Creator,
    @Relation(
        parentColumn = "narrator_id",
        entityColumn = "id"
    )
    val narrator: Narrator
)


@Entity(
    tableName = "streams",
    foreignKeys = [
        ForeignKey(
            entity = Narrator::class,
            parentColumns = ["id"],
            childColumns = ["narrator_id"],
            onDelete = CASCADE
        )
    ]
)
data class Stream(
    @PrimaryKey(
        autoGenerate = true
    ) val id: Long,

    @ColumnInfo(name = "narrator_id")
    val narratorId: String,

    @ColumnInfo(name = "preview_url")
    val previewUrl: String,

    val name: String,
    val description: String,

)

@Entity(
    tableName = "clips",
    foreignKeys = [
        ForeignKey(
            entity = Narrator::class,
            parentColumns = ["id"],
            childColumns = ["narrator_id"],
            onDelete = NO_ACTION
        ),
        ForeignKey(
            entity = Creator::class,
            parentColumns = ["id"],
            childColumns = ["creator_id"],
            onDelete = NO_ACTION
        ),
    ],
)
data class Clip(
    @PrimaryKey(
        autoGenerate = true
    ) val id: Long,

    @ColumnInfo(name = "preview_url")
    val previewUrl: String,

    val name: String,
    val description: String,
    val duration: String,

    @ColumnInfo(name = "creator_id")
    val creatorId: Long,

    @ColumnInfo(name = "narrator_id")
    val narratorId: Long,
)

@Entity(
    tableName = "narrators",
)
data class Narrator(
    @PrimaryKey(
        autoGenerate = true
    ) val id: Long,

    val avatar: String,
    val name: String,
)

@Entity(
    tableName = "creators",
)
data class Creator(
    @PrimaryKey(
        autoGenerate = true
    ) val id: Long,

    val avatar: String,
    val name: String,
)


@Entity(
    tableName = "games",
)
data class Game(
    @PrimaryKey() val id: String,
    val description: String,
    val name: String,
)


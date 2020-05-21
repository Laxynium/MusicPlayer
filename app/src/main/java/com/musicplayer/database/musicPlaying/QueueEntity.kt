package com.musicplayer.database.musicPlaying

import androidx.room.*

@Entity(tableName = "queue_songs")
data class SongEntity(
    @PrimaryKey val id: String,
    val queueId:Int,
    val location: String,
    val position:Int
)

@Entity(tableName = "queue")
data class QueueEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "current_song_index")val currentSongIndex: Int
)

data class QueueWithSongsEntity(
    @Embedded val queue:QueueEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "queueId"
    )
    val songs: List<SongEntity>
)
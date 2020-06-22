package com.musicplayer.database.musicPlaying

import androidx.room.*
import java.util.*

@Entity(tableName = "queue_songs", primaryKeys = ["id","position"])
data class SongEntity(
    val id: UUID,
    val queueId:Int,
    val location: String,
    val position:Int
)

@Entity(tableName = "queue")
data class QueueEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "current_song_index") val currentSongIndex: Int
)

data class QueueWithSongsEntity(
    @Embedded val queue:QueueEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "queueId"
    )
    val songs: List<SongEntity>
)

@DatabaseView(
    value="""
SELECT s.songId, qS.position, s.title, s.thumbnailUrl, s.location, qS.position = q.current_song_index as isCurrent  
FROM queue_songs qS 
INNER JOIN queue q ON q.id = qS.queueId
INNER JOIN songs s ON s.songId = qS.id
ORDER BY qS.position
    """,
    viewName = "queue_songs_view"
)
data class QueueSongView(
    val songId:UUID,
    val position: Int,
    val title: String,
    val thumbnailUrl: String,
    val location: String,
    val isCurrent: Boolean
)
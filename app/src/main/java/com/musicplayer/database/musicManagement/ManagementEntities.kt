package com.musicplayer.database.musicManagement

import androidx.room.*
import java.util.*

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey val playlistId: UUID,
    val name: String
)

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey val songId: UUID,
    val ytId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String,
    val location: String?
)

@Entity(primaryKeys = ["playlistId", "songId"])
data class PlaylistSongCrossRef(
    val playlistId: UUID,
    val songId: UUID
)

data class PlaylistWithSongsEntity(
    @Embedded val playlist: PlaylistEntity,
    @Relation(
        parentColumn = "playlistId",
        entity = SongEntity::class,
        entityColumn = "songId",
        associateBy = Junction(
                value = PlaylistSongCrossRef::class,
                parentColumn = "playlistId",
                entityColumn = "songId"
        )
    )
    val songs: List<SongEntity>
)

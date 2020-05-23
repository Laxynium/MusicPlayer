package com.musicplayer.database.musicManagement

import androidx.room.*

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey val playlistId: Long,
    val name: String
)

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey val songId: Long,
    val location: String
)

@Entity(primaryKeys = ["playlistId", "songId"])
data class PlaylistSongCrossRef(
    val playlistId: Long,
    val songId: Long
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

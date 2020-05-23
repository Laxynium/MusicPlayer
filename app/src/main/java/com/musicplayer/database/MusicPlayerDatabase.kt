package com.musicplayer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.musicplayer.database.musicManagement.PlaylistDao
import com.musicplayer.database.musicManagement.PlaylistEntity
import com.musicplayer.database.musicManagement.PlaylistSongCrossRef
import com.musicplayer.database.musicManagement.SongEntity as mmSongEntity
import com.musicplayer.database.musicPlaying.QueueDao
import com.musicplayer.database.musicPlaying.QueueEntity
import com.musicplayer.database.musicPlaying.SongEntity as mpSongEntity

@Database(
    entities =
    [
        QueueEntity::class,
        mpSongEntity::class,
        PlaylistEntity::class,
        mmSongEntity::class,
        PlaylistSongCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MusicPlayerDatabase : RoomDatabase() {
    abstract fun queueDao(): QueueDao
    abstract fun playlistDao(): PlaylistDao
}
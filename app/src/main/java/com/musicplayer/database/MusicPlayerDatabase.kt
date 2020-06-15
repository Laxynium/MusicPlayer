package com.musicplayer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.musicplayer.database.musicManagement.*
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
@TypeConverters(Converters::class)
abstract class MusicPlayerDatabase : RoomDatabase() {
    abstract fun queueDao(): QueueDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun songDao(): SongDao
    abstract fun playlistReadDao(): PlaylistReadDao
}
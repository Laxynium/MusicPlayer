package com.musicplayer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.musicplayer.database.musicManagement.*
import com.musicplayer.database.musicManagement.SongEntity as mmSongEntity
import com.musicplayer.database.musicPlaying.QueueWriteDao
import com.musicplayer.database.musicPlaying.QueueEntity
import com.musicplayer.database.musicPlaying.SongEntity as mpSongEntity
import com.musicplayer.database.musicPlaying.QueueReadDao
import com.musicplayer.database.musicPlaying.QueueSongView

@Database(
    entities =
    [
        QueueEntity::class,
        mpSongEntity::class,
        PlaylistEntity::class,
        mmSongEntity::class,
        PlaylistSongCrossRef::class
    ],
    views = [
        QueueSongView::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MusicPlayerDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
    abstract fun songDao(): SongDao
    abstract fun playlistReadDao(): PlaylistReadDao
    abstract fun queueWriteDao(): QueueWriteDao
    abstract fun queueReadDao(): QueueReadDao
}
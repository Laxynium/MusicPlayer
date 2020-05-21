package com.musicplayer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.musicplayer.database.musicPlaying.QueueDao
import com.musicplayer.database.musicPlaying.QueueEntity
import com.musicplayer.database.musicPlaying.SongEntity

@Database(
    entities =
    [
        QueueEntity::class,
        SongEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MusicPlayerDatabase : RoomDatabase() {
    abstract fun queueDao(): QueueDao
}
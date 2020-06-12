package com.musicplayer.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.musicplayer.database.musicPlaying.QueueReadDao
import com.musicplayer.database.musicPlaying.QueueWriteDao
import com.musicplayer.database.musicPlaying.RoomQueueRepository
import com.musicplayer.musicPlaying.domain.QueueRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

object DatabaseModule {
    val koinModule = module{
        single {
            Room.databaseBuilder(
                androidContext(),
                MusicPlayerDatabase::class.java,
                "musicPlayerDb")
                .addCallback(object: RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        db.execSQL("INSERT OR IGNORE INTO queue (id, current_song_index) VALUES(1,0)")
                    }
                })
                .build()
        }
        single { get<MusicPlayerDatabase>().queueWriteDao() } bind QueueWriteDao::class
        single { get<MusicPlayerDatabase>().queueReadDao() } bind QueueReadDao::class
        single { RoomQueueRepository(get()) } bind QueueRepository::class
    }
}
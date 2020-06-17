package com.musicplayer.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.musicplayer.database.musicManagement.*
import com.musicplayer.database.musicPlaying.QueueDao
import com.musicplayer.database.musicPlaying.RoomQueueRepository
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import com.musicplayer.musicManagement.repositories.SongRepository
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
                        db.execSQL("INSERT OR IGNORE INTO playlists (playlistId, name) VALUES('00000000-0000-0000-0000-000000000001','main')")
                    }
                })
                .build()
        }
        single { get<MusicPlayerDatabase>().queueDao() } bind QueueDao::class
        single { RoomQueueRepository(get()) } bind QueueRepository::class
        single { PlaylistDatabaseRepository(get(), get()) } bind PlaylistRepository::class
        single { SongDatabaseRepository(get()) } bind SongRepository::class
        single { get<MusicPlayerDatabase>().playlistReadDao()} bind PlaylistReadDao::class
        single { get<MusicPlayerDatabase>().playlistDao()} bind PlaylistDao::class
        single { get<MusicPlayerDatabase>().songDao()} bind SongDao::class
    }
}
package com

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.musicplayer.database.MusicPlayerDatabase
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

interface SetupDb{
    @Before
    fun setupDb(){
        loadKoinModules(listOf(
            module{
                single(override = true) {
                    Room.inMemoryDatabaseBuilder(
                        androidContext(),
                        MusicPlayerDatabase::class.java
                    ).addCallback(object: RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            db.execSQL("INSERT OR IGNORE INTO queue (id, current_song_index) VALUES(1,0)")
                        }
                    }).build()
                }
                single(override = true) { get<MusicPlayerDatabase>().queueWriteDao() }
            }
        ))
    }
}
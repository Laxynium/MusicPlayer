package com.musicplayer

import android.app.Application
import androidx.room.Room
import com.musicplayer.musicBrowsing.MusicBrowsingModule
import com.musicplayer.musicManagement.MusicManagementModule
import com.musicplayer.musicPlaying.MusicPlayingModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MusicPlayerApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            val koin = this.koin

            androidLogger()
            androidContext(this@MusicPlayerApplication)
            modules(
                module{ single { koin } },// used for dispatching queries and commands
                MusicBrowsingModule.koinModule,
                MusicManagementModule.koinModule,
                MusicPlayingModule.koinModule,
                MusicPlayerModule.module,
                module {
                    single {
                        Room.databaseBuilder(
                        applicationContext,
                        MusicPlayerDatabase::class.java,
                        "musicPlayerDb").build()
                    }
                    single { get<MusicPlayerDatabase>().songDao() }
                }
            )
        }
    }
}
package com.musicplayer

import android.app.Application
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
                MusicPlayerModule.module,
                module{
                    single { koin }
                }
            )

        }
    }
}
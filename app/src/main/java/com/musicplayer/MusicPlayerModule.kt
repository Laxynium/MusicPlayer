package com.musicplayer

import com.musicplayer.framework.messaging.MessageBus
import org.koin.core.module.Module
import org.koin.dsl.module

object MusicPlayerModule {
    val module: Module = module {
        single { MessageBus(get()) }
    }
}
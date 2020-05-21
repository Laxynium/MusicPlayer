package com.musicplayer.musicPlaying

import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicPlaying.domain.commands.queue.*
import com.musicplayer.musicPlaying.queries.GetSongsInQueueHandler
import com.musicplayer.musicPlaying.ui.MusicPlayingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object MusicPlayingModule {
    val koinModule = module {
        viewModel { MusicPlayingViewModel(get()) }
        single { EnqueueSongHandler(get()) } bind CommandHandler::class
        single { EnqueueSongAsNextHandler(get()) } bind CommandHandler::class
        single { GotToNextSongHandler(get()) } bind CommandHandler::class
        single { GoToPreviousSongHandler(get()) } bind CommandHandler::class
        single { GoToSongHandler(get()) } bind CommandHandler::class
        single { GetSongsInQueueHandler(get()) } bind QueryHandler::class
    }
}
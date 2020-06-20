package com.musicplayer.musicPlaying

import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicPlaying.domain.commands.player.*
import com.musicplayer.musicPlaying.domain.commands.queue.*
import com.musicplayer.musicPlaying.queries.GetPlayingStatus
import com.musicplayer.musicPlaying.queries.GetPlayingStatusHandler
import com.musicplayer.musicPlaying.queries.GetSongProgressHandler
import com.musicplayer.musicPlaying.queries.GetSongsInQueueHandler
import com.musicplayer.musicPlaying.ui.MusicPlayingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object MusicPlayingModule {
    val koinModule = module {
        viewModel { MusicPlayingViewModel(get(),get()) }
        single { EnqueuePlaylistHandler(get()) } bind CommandHandler::class
        single { EnqueueSongHandler(get()) } bind CommandHandler::class
        single { EnqueueSongAsNextHandler(get()) } bind CommandHandler::class
        single { GoToNextSongHandler(get(), get()) } bind CommandHandler::class
        single { GoToPreviousSongHandler(get(),get()) } bind CommandHandler::class
        single { GoToSongHandler(get(),get()) } bind CommandHandler::class

        single { PlaySongHandler(get(), get()) } bind CommandHandler::class
        single { PauseSongHandler(get(),get()) } bind CommandHandler::class
        single { SeekToSecondHandler(get(),get()) } bind CommandHandler::class
        single(createdAtStart = true) { Coordinator(get(),get()) }
        single { DevicePlayer(get()) } bind IDevicePlayer::class

        single { GetSongsInQueueHandler(get()) } bind QueryHandler::class
        single { GetSongProgressHandler(get()) } bind QueryHandler::class
        single { GetPlayingStatusHandler(get()) } bind QueryHandler::class
    }
}
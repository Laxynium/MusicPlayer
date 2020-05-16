package com.musicplayer.musicPlaying

import com.musicplayer.musicPlaying.ui.MusicPlayingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MusicPlayingModule {
    val koinModule = module {
        viewModel { MusicPlayingViewModel(get()) }
    }
}
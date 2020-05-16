package com.musicplayer.musicManagement

import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.mainPlaylist.AddSongFromYoutubeHandler
import com.musicplayer.musicManagement.mainPlaylist.RemoveSongFromMainPlaylistHandler
import com.musicplayer.musicManagement.regularPlaylist.CreateRegularPlaylistHandler
import com.musicplayer.musicManagement.regularPlaylist.RemoveRegularPlaylistHandler
import com.musicplayer.musicManagement.regularPlaylist.RemoveSongFromRegularPlaylistHandler
import com.musicplayer.musicManagement.regularPlaylist.RenameRegularPlaylistHandler
import com.musicplayer.musicManagement.ui.MusicManagementViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object MusicManagementModule {
    val koinModule = module {
        viewModel { MusicManagementViewModel(get()) }
        single { AddSongFromYoutubeHandler() } bind CommandHandler::class
        single { RemoveSongFromMainPlaylistHandler() } bind CommandHandler::class
        single { CreateRegularPlaylistHandler() } bind CommandHandler::class
        single { RemoveRegularPlaylistHandler() } bind CommandHandler::class
        single { RemoveSongFromRegularPlaylistHandler() } bind CommandHandler::class
        single { RenameRegularPlaylistHandler() } bind CommandHandler::class
    }
}
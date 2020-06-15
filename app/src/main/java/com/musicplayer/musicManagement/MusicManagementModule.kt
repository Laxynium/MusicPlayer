package com.musicplayer.musicManagement

import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicManagement.mainPlaylist.AddSongFromYoutubeHandler
import com.musicplayer.musicManagement.mainPlaylist.RemoveSongFromMainPlaylistHandler
import com.musicplayer.musicManagement.regularPlaylist.*
import com.musicplayer.musicManagement.ui.MusicManagementViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object MusicManagementModule {
    val koinModule = module {
        viewModel { MusicManagementViewModel(get()) }
        single { AddSongFromYoutubeHandler(get()) } bind CommandHandler::class
        single { AddSongToRegularPlaylistHandler(get(),get()) } bind CommandHandler::class
        single { RemoveSongFromMainPlaylistHandler(get()) } bind CommandHandler::class
        single { CreateRegularPlaylistHandler(get()) } bind CommandHandler::class
        single { RemoveRegularPlaylistHandler(get()) } bind CommandHandler::class
        single { RemoveSongFromRegularPlaylistHandler(get()) } bind CommandHandler::class
        single { RenameRegularPlaylistHandler(get()) } bind CommandHandler::class

        single { GetAllPlaylistsHandler(get()) } bind QueryHandler::class
        single { GetPlaylistByIdHandler(get()) } bind QueryHandler::class
        single { GetAllSongsFromPlaylistHandler(get()) } bind QueryHandler::class
    }
}
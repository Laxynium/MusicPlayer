package com.musicplayer

import com.musicplayer.framework.CommandHandler
import com.musicplayer.framework.MessageBus
import com.musicplayer.framework.QueryHandler
import com.musicplayer.musicBrowsing.search.SearchSongQueryHandler
import com.musicplayer.musicBrowsing.search.ui.SearchSongViewModel
import com.musicplayer.musicBrowsing.search.TestService
import com.musicplayer.musicManagement.mainPlaylist.AddSongFromYoutubeHandler
import com.musicplayer.musicManagement.mainPlaylist.RemoveSongFromMainPlaylistHandler
import com.musicplayer.musicManagement.regularPlaylist.CreateRegularPlaylistHandler
import com.musicplayer.musicManagement.regularPlaylist.RemoveRegularPlaylistHandler
import com.musicplayer.musicManagement.regularPlaylist.RemoveSongFromRegularPlaylistHandler
import com.musicplayer.musicManagement.regularPlaylist.RenameRegularPlaylistHandler
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

object MusicPlayerModule {
    val module: Module = module {
        single { TestService() }
        viewModel {
            SearchSongViewModel(
                get()
            )
        }
        single { MessageBus(get()) }
        single { SearchSongQueryHandler()} bind QueryHandler::class
        single { AddSongFromYoutubeHandler() } bind CommandHandler::class
        single { RemoveSongFromMainPlaylistHandler() } bind CommandHandler::class
        single { CreateRegularPlaylistHandler() } bind CommandHandler::class
        single { RemoveRegularPlaylistHandler() } bind CommandHandler::class
        single { RemoveSongFromRegularPlaylistHandler() } bind CommandHandler::class
        single { RenameRegularPlaylistHandler() } bind CommandHandler::class
    }
}
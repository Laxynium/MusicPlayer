package com.musicplayer.musicBrowsing

import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicBrowsing.search.domain.SearchSongQueryHandler
import com.musicplayer.musicBrowsing.search.ui.SearchSongViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object MusicBrowsingModule {
    val koinModule = module {
        viewModel { SearchSongViewModel(get()) }
        single { SearchSongQueryHandler() } bind QueryHandler::class
    }
}

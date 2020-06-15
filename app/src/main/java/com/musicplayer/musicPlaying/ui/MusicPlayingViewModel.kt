package com.musicplayer.musicPlaying.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.regularPlaylist.GetAllPlaylists
import kotlinx.coroutines.launch

class MusicPlayingViewModel(private val messageBus: MessageBus): ObservableViewModel() {
//    private lateinit var playlists: List<Playlist>
//
//    init {
//        viewModelScope.launch {
//            playlists = messageBus.dispatch(GetAllPlaylists())
//
//        }
//    }

    fun play(){
    }
}
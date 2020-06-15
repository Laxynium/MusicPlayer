package com.musicplayer.musicManagement.ui

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.musicplayer.R
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.regularPlaylist.CreateRegularPlaylist
import com.musicplayer.musicManagement.regularPlaylist.GetAllPlaylists
import com.musicplayer.musicManagement.regularPlaylist.GetAllPlaylistsHandler
import com.musicplayer.musicPlaying.domain.commands.queue.EnqueueSong
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MusicManagementViewModel(private val messageBus: MessageBus): ObservableViewModel() {
    private lateinit var playlists: LiveData<List<Playlist>>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            messageBus.dispatch(CreateRegularPlaylist(UUID.randomUUID(),"new"))
        }

        viewModelScope.launch {
            playlists = messageBus.dispatch(GetAllPlaylists())
        }
    }

    fun addPlaylist() {

    }

    fun removePlaylist() {

    }

    fun addToPlaylist() {

    }

    fun addSong() {

    }


}
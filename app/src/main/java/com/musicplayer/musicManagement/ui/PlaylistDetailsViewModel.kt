package com.musicplayer.musicManagement.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.regularPlaylist.GetAllPlaylists
import com.musicplayer.musicManagement.regularPlaylist.GetAllSongsFromPlaylist
import kotlinx.coroutines.launch

class PlaylistDetailsViewModel(private val messageBus: MessageBus): ObservableViewModel() {
    private lateinit var playlist: Playlist
    private lateinit var songs: LiveData<List<Song>>
    private lateinit var onSongsChangedHandler: (List<Song>)->Unit

    init {
        viewModelScope.launch {
            songs = messageBus.dispatch(GetAllSongsFromPlaylist(playlist.playlistId))
            songs.observeForever {
                onSongsChangedHandler.invoke(it)
            }
        }
    }

    fun onSongsChanged(`fun`:(List<Song>)->Unit){
        onSongsChangedHandler = `fun`
    }


}
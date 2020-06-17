package com.musicplayer.musicManagement.ui

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.musicplayer.MainActivity
import com.musicplayer.R
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.regularPlaylist.GetAllPlaylists
import com.musicplayer.musicManagement.regularPlaylist.RemoveRegularPlaylist
import kotlinx.coroutines.launch
import java.util.*


class MusicManagementViewModel(private val messageBus: MessageBus): ObservableViewModel() {
    private lateinit var playlists: LiveData<List<Playlist>>
    private lateinit var onPlaylistsChangedHandler: (List<Playlist>)->Unit
    private lateinit var songs: LiveData<List<Song>>
    private lateinit var onSongsChangedHandler: (List<Song>)->Unit=

    init {
        viewModelScope.launch {
            playlists = messageBus.dispatch(GetAllPlaylists())
            playlists.observeForever {
                onPlaylistsChangedHandler.invoke(it)
            }
        }
    }

    fun onPlaylistsChange(`fun`:(List<Playlist>)->Unit){
        onPlaylistsChangedHandler = `fun`
    }

    fun goTo(playlist: Playlist) {
//        var ft = mainActivity.supportFragmentManager.beginTransaction()
//        ft.replace(R.id.navigation_musicManagement, PlaylistDetailsFragment())
//        ft.commit()
    }

    fun addPlaylistButtonClick() {

    }

    fun removePlaylist(id: UUID) {
        viewModelScope.launch {
            messageBus.dispatch(RemoveRegularPlaylist(id))
        }
    }

    fun addToPlaylist() {

    }

    fun addSong() {

    }


}
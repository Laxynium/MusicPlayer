package com.musicplayer.musicManagement.ui

import android.app.Activity
import android.view.View
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
    private lateinit var parentFragment: MusicManagementFragment

    init {
        viewModelScope.launch {
            playlists = messageBus.dispatch(GetAllPlaylists())
            playlists.observeForever {
                onPlaylistsChangedHandler.invoke(it)
            }
        }
    }

    fun setParent(parent: MusicManagementFragment){
        this.parentFragment = parent
    }

    fun onPlaylistsChange(`fun`:(List<Playlist>)->Unit){
        onPlaylistsChangedHandler = `fun`
    }

    fun goTo(playlist: Playlist) {
        parentFragment.moveToDetails(playlist)
    }

    fun goToAddPlaylist(view: View) {
        parentFragment.moveToAddPlaylist()
    }

    fun remove(playlist: Playlist) {
        viewModelScope.launch {
            messageBus.dispatch(RemoveRegularPlaylist(playlist.playlistId))
        }
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
package com.musicplayer.musicManagement.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicManagement.mainPlaylist.RemoveSongFromMainPlaylist
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.regularPlaylist.GetAllSongsFromPlaylist
import com.musicplayer.musicManagement.regularPlaylist.GetAllSongsFromPlaylistNotLiveData
import com.musicplayer.musicManagement.regularPlaylist.RemoveSongFromRegularPlaylist
import com.musicplayer.musicPlaying.domain.commands.queue.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class PlaylistDetailsViewModel(private val messageBus: MessageBus) : ObservableViewModel() {
    private lateinit var playlist: Playlist
    private lateinit var songs: LiveData<List<Song>>
    private lateinit var onSongsChangedHandler: (List<Song>) -> Unit
    private lateinit var parentFragment: PlaylistDetailsFragment

    fun setParent(parent: PlaylistDetailsFragment) {
        this.parentFragment = parent
    }

    fun setup(playlist: Playlist) {
        this.playlist = playlist
        viewModelScope.launch {
            songs = messageBus.dispatch(GetAllSongsFromPlaylist(playlist.playlistId))
            songs.observeForever {
                onSongsChangedHandler.invoke(it)
            }
        }
    }

    fun onSongsChanged(`fun`: (List<Song>) -> Unit) {
        onSongsChangedHandler = `fun`
    }

    fun enqueueWholePlaylist() {
        viewModelScope.launch(Dispatchers.IO) {
            val activeSongs = messageBus.dispatch(GetAllSongsFromPlaylistNotLiveData(playlist.playlistId))

            messageBus.dispatch(EnqueuePlaylist(activeSongs.map { PlaylistSongDto(it.songId, it.location!!) }))

            parentFragment.moveToPlayingView()
        }
    }

    fun goTo(song: Song) {
        viewModelScope.launch {
            messageBus.dispatch(EnqueueSongAsNext(song.songId, song.location.toString()))
            parentFragment.moveToPlayingView()
        }
    }

    fun remove(song: Song) {
        viewModelScope.launch {
            if (playlist.playlistId.equals(UUID.fromString("00000000-0000-0000-0000-000000000001"))) {
                messageBus.dispatch(RemoveSongFromMainPlaylist(song.songId, song.location))
            } else {
                messageBus.dispatch(RemoveSongFromRegularPlaylist(playlist.playlistId, song.songId))
            }
        }
    }

    fun goBack() {
        parentFragment.moveBack()
    }


}
package com.musicplayer.musicManagement.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.regularPlaylist.AddSongToRegularPlaylist
import com.musicplayer.musicManagement.regularPlaylist.GetAllRegularPlaylists
import com.musicplayer.musicPlaying.domain.commands.player.PlaySong
import com.musicplayer.musicPlaying.domain.commands.queue.EnqueueSong
import com.musicplayer.musicPlaying.domain.commands.queue.EnqueueSongAsNext
import com.musicplayer.musicPlaying.domain.commands.queue.GoToNextSong
import kotlinx.coroutines.launch


class SongContextViewModel(private val messageBus: MessageBus) : ObservableViewModel() {
    private lateinit var parentFragment: SongContextFragment
    private lateinit var playlists: LiveData<List<Playlist>>
    private lateinit var onPlaylistsChangedHandler: (List<Playlist>) -> Unit
    private lateinit var song: Song

    init {
        viewModelScope.launch {
            playlists = messageBus.dispatch(GetAllRegularPlaylists())
            playlists.observeForever {
                onPlaylistsChangedHandler.invoke(it)
            }
        }
    }

    fun onPlaylistsChange(`fun`: (List<Playlist>) -> Unit) {
        onPlaylistsChangedHandler = `fun`
    }

    fun enqueue(view: View) {
        viewModelScope.launch {
            messageBus.dispatch(EnqueueSong(song.songId, song.location.toString()))
        }
    }

    fun playNow(view: View) {
        viewModelScope.launch {
            messageBus.dispatch(EnqueueSongAsNext(song.songId, song.location.toString()))
            messageBus.dispatch(GoToNextSong())
            messageBus.dispatch(PlaySong())
        }
        parentFragment.moveToPlaying()
    }

    fun setSong(song: Song) {
        this.song = song
    }

    fun setParent(parent: SongContextFragment) {
        this.parentFragment = parent
    }

    fun goBack(view: View) {
        parentFragment.moveBack()
    }

    fun addToPlaylist(playlist: Playlist) {
        viewModelScope.launch {
            messageBus.dispatch(AddSongToRegularPlaylist(playlist.playlistId, song.songId))
        }
        parentFragment.moveBack()
    }
}
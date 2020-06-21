package com.musicplayer.musicPlaying.ui

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicPlaying.domain.commands.player.PauseSong
import com.musicplayer.musicPlaying.domain.commands.player.PlaySong
import com.musicplayer.musicPlaying.domain.commands.player.SeekToSecond
import com.musicplayer.musicPlaying.domain.commands.queue.GoToNextSong
import com.musicplayer.musicPlaying.domain.commands.queue.GoToPreviousSong
import com.musicplayer.musicPlaying.domain.commands.queue.GoToSong
import com.musicplayer.musicPlaying.domain.commands.queue.RemoveSongFromQueue
import com.musicplayer.musicPlaying.queries.GetPlayingStatus
import com.musicplayer.musicPlaying.queries.GetSongProgress
import com.musicplayer.musicPlaying.queries.GetSongsInQueue
import com.musicplayer.musicPlaying.queries.SongDto
import kotlinx.coroutines.launch

class MusicPlayingViewModel(private val messageBus: MessageBus): ObservableViewModel() {
    private var songsInQueue: MutableLiveData<List<SongDto>> = MutableLiveData()

    @Bindable
    val songProgress = MutableLiveData<Int>()

    @Bindable
    val playing = MutableLiveData<String>().apply {
        value = "Play"
    }

    init {
            viewModelScope.launch {
                messageBus.dispatch(GetSongsInQueue())
                    .observeForever {
                        songsInQueue.postValue(it)
                    }
            }

            viewModelScope.launch {
                messageBus.dispatch(GetSongProgress()).observeForever {
                    songProgress.postValue(it)
                }
            }

            viewModelScope.launch {
                messageBus.dispatch(GetPlayingStatus()).observeForever {
                    val value = if (it.isPlaying) "Pause" else "Play"
                    playing.postValue(value)
                }
            }
    }

    fun songsObservable():LiveData<List<SongDto>>{
        return songsInQueue
    }

    fun toggle(){
        if(playing.value != "Play"){
            pause()
        }else{
            play()
        }
    }

    private fun play() = viewModelScope.launch {
        messageBus.dispatch(PlaySong())
    }

    private fun pause() = viewModelScope.launch {
        messageBus.dispatch(PauseSong())
    }

    fun next() = viewModelScope.launch {
        messageBus.dispatch(GoToNextSong())
    }

    fun prev() = viewModelScope.launch {
        messageBus.dispatch(GoToPreviousSong())
    }

    fun goTo(songDto: SongDto) = viewModelScope.launch {
        messageBus.dispatch(GoToSong(songDto.position))
    }

    fun seekTo(progress:Int) {
        viewModelScope.launch {
            messageBus.dispatch(SeekToSecond(progress))
        }
    }

    fun remove(songDto: SongDto)=viewModelScope.launch {
        messageBus.dispatch(RemoveSongFromQueue(songDto.songId, songDto.position))
    }
}
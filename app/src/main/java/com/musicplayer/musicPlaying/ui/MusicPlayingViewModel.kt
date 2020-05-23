package com.musicplayer.musicPlaying.ui

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.musicplayer.R
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicPlaying.domain.commands.player.PauseSong
import com.musicplayer.musicPlaying.domain.commands.player.PlaySong
import com.musicplayer.musicPlaying.domain.commands.queue.EnqueueSong
import com.musicplayer.musicPlaying.domain.commands.queue.GoToNextSong
import com.musicplayer.musicPlaying.domain.commands.queue.GoToPreviousSong
import kotlinx.coroutines.launch
import java.util.*

class MusicPlayingViewModel(private val messageBus: MessageBus,private val context: Context): ObservableViewModel() {
    private var isPlaying = false
    init {
            viewModelScope.launch {
                val resourceIds = listOf(R.raw.sample_1, R.raw.sample_2, R.raw.sample_3)
                resourceIds.forEach { resourceId->
                    val resources = context.resources
                    val uri = Uri.Builder()
                        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                        .authority((resources.getResourcePackageName(resourceId)))
                        .appendPath((resources.getResourceTypeName(resourceId)))
                        .appendPath((resources.getResourceEntryName(resourceId)))
                        .build()
                messageBus.dispatch(EnqueueSong(UUID.randomUUID(), uri.toString()))
            }
        }
    }
    fun toggle(){
        if(isPlaying){
            pause()
        }else{
            play()
        }
        isPlaying = !isPlaying
    }
    private fun play() = viewModelScope.launch{
        messageBus.dispatch(PlaySong())
    }

    private fun pause() = viewModelScope.launch{
        messageBus.dispatch(PauseSong())
    }

    fun next() = viewModelScope.launch {
        messageBus.dispatch(GoToNextSong())
    }

    fun prev() = viewModelScope.launch {
        messageBus.dispatch(GoToPreviousSong())
    }


    fun seekTo(){

    }
}
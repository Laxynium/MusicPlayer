package com.musicplayer.musicPlaying.ui

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import com.musicplayer.R
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicPlaying.domain.commands.player.PauseSong
import com.musicplayer.musicPlaying.domain.commands.player.PlaySong
import com.musicplayer.musicPlaying.domain.commands.queue.EnqueueSong
import com.musicplayer.musicPlaying.domain.commands.queue.GoToNextSong
import com.musicplayer.musicPlaying.domain.commands.queue.GoToPreviousSong
import java.util.*

class MusicPlayingViewModel(private val messageBus: MessageBus,private val context: Context): ObservableViewModel() {
    private var isPlaying = false
    init {
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
    fun toggle(){
        if(isPlaying){
            pause()
        }else{
            play()
        }
        isPlaying = !isPlaying
    }
    fun play(){
        messageBus.dispatch(PlaySong())
    }

    fun pause(){
        messageBus.dispatch(PauseSong())
    }

    fun next(){
        messageBus.dispatch(GoToNextSong())
    }

    fun prev(){
        messageBus.dispatch(GoToPreviousSong())
    }

    fun seekTo(){

    }
}
package com.musicplayer.musicPlaying.domain.commands.player

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DevicePlayer(private val context: Context) : IDevicePlayer {
    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private var isPrepared = false
    private var isPlaying = false

    override fun onSongEnded(action:suspend ()->Unit){
        mediaPlayer.setOnCompletionListener {
            GlobalScope.launch {
                action()
            }
        }
    }

    override fun isReady() = isPrepared
    override fun isPlaying() = isPlaying


    override suspend fun changeSong(songLocation: String) =
        suspendCoroutine<Unit> { cont->
            reset()
            mediaPlayer.setDataSource(context, Uri.parse(songLocation))
            mediaPlayer.setOnPreparedListener {
                isPrepared = true
                cont.resume(Unit)
            }
            mediaPlayer.prepareAsync()
        }

    override fun play(){
        isPlaying = true
        mediaPlayer.start()
    }

    override fun pause(){
        if(!isPrepared){
            return
        }
        if(!isPlaying){
            return
        }
        isPlaying = false
        mediaPlayer.pause()
    }

    override fun seekTo(sec:Int){
        if(!isPrepared){
            return
        }
        mediaPlayer.seekTo(sec / 1000)
    }

    private fun reset(){
        isPrepared = false
        mediaPlayer.reset()
    }
}
package com.musicplayer.musicPlaying.domain.commands.player

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

class DevicePlayer(private val context: Context) : IDevicePlayer {
    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private var isPrepared = false
    private var isPlaying = false

    override fun onSongEnded(action:()->Unit){
        mediaPlayer.setOnCompletionListener {
            action()
        }
    }

    override fun isReady() = isPrepared
    override fun isPlaying() = isPlaying


    override fun changeSong(songLocation:String, onChanged: ()->Unit){
        reset()

        mediaPlayer.setDataSource(context, Uri.parse(songLocation))
        mediaPlayer.setOnPreparedListener {
            isPrepared = true
            onChanged()
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
package com.musicplayer.musicPlaying.domain.commands.player

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DevicePlayer(private val context: Context) : IDevicePlayer {
    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private var isPrepared = false
    private val isPlaying = MutableLiveData<Boolean>()
    private val currentPosition: LiveData<Int> = liveData(Dispatchers.IO) {
        while (true){
            if(!mediaPlayer.isPlaying)
                continue

            try {
                val value =
                    (mediaPlayer.currentPosition.toDouble() / mediaPlayer.duration * 100).toInt()
                emit(value)
                delay(10)
            }catch (exception: Exception){
                print(exception)
            }
        }
    }

    override fun onSongEnded(action:suspend ()->Unit){
        mediaPlayer.setOnCompletionListener {
            GlobalScope.launch {
                action()
            }
        }
    }

    override fun currentPosition():LiveData<Int>{
        return currentPosition
    }

    override fun isReady() = isPrepared
    override fun isPlaying() = isPlaying


    override suspend fun changeSong(songLocation: String) =
        suspendCoroutine<Unit> { cont->
            val wasPlaying = mediaPlayer.isPlaying
            reset()
            mediaPlayer.setDataSource(context, Uri.parse(songLocation))
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.seekTo(0)
                isPrepared = true
                cont.resume(Unit)
                if(wasPlaying)
                    play()
            }
            mediaPlayer.prepareAsync()
        }

    override fun play(){
        mediaPlayer.start()
        isPlaying.postValue(true)
    }

    override fun pause(){
        if(!isPrepared){
            return
        }
        if(!mediaPlayer.isPlaying){
            return
        }
        mediaPlayer.pause()
        isPlaying.postValue(false)
    }

    override fun seekTo(progressInPercentage:Int){
        if(!isPrepared){
            return
        }
        val newProgress = (mediaPlayer.duration * progressInPercentage / 100)
        mediaPlayer.seekTo(newProgress)
    }

    private fun reset(){
        isPrepared = false
        mediaPlayer.reset()
    }
}
package com.musicplayer.musicPlaying.domain.commands.player

import androidx.lifecycle.LiveData

interface IDevicePlayer {
    fun onSongEnded(action: suspend () -> Unit)
    fun currentPosition(): LiveData<Int>
    fun isReady(): Boolean
    fun isPlaying(): LiveData<Boolean>
    suspend fun changeSong(songLocation: String)
    fun play()
    fun pause()
    fun seekTo(progressInPercentage: Int)
}
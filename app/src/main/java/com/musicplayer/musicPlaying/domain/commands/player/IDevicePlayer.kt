package com.musicplayer.musicPlaying.domain.commands.player

interface IDevicePlayer {
    fun onSongEnded(action: suspend () -> Unit)
    fun isReady(): Boolean
    fun isPlaying(): Boolean
    suspend fun changeSong(songLocation: String)
    fun play()
    fun pause()
    fun seekTo(sec: Int)
}
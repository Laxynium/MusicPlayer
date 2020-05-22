package com.musicplayer.musicPlaying.domain.commands.player

interface IDevicePlayer {
    fun onSongEnded(action: () -> Unit)
    fun isReady(): Boolean
    fun isPlaying(): Boolean
    fun changeSong(songLocation: String, onChanged: () -> Unit)
    fun play()
    fun pause()
    fun seekTo(sec: Int)
}
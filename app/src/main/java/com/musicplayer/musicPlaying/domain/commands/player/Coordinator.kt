package com.musicplayer.musicPlaying.domain.commands.player

import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.musicPlaying.domain.commands.queue.GoToNextSong

class Coordinator(devicePlayer: IDevicePlayer, private val messageBus: MessageBus){
    init {
        devicePlayer.onSongEnded{onSongEnded()}
    }
    private suspend fun onSongEnded() {
        messageBus.dispatch(GoToNextSong())
    }
}
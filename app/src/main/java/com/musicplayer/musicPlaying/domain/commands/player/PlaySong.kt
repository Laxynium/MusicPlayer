package com.musicplayer.musicPlaying.domain.commands.player

import arrow.core.Some
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicPlaying.domain.QueueRepository

class PlaySong : Command
class PlaySongHandler(private val queueRepository: QueueRepository, private val devicePlayer: IDevicePlayer): CommandHandler<PlaySong>{
    override fun handle(command: PlaySong) {
        if(!devicePlayer.isReady()){
            val queue = queueRepository.get()
            when(val song = queue.currentSong()){
                is Some -> {
                    devicePlayer.changeSong(song.t.location){
                        devicePlayer.play()
                    }
                }
            }
        }else{
            devicePlayer.play()
        }
    }
}


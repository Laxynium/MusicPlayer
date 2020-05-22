package com.musicplayer.musicPlaying.domain.commands.player

import arrow.core.Some
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicPlaying.domain.QueueRepository

data class SeekToSecond(val second:Int) : Command
class SeekToSecondHandler(private val queueRepository: QueueRepository, private val devicePlayer: IDevicePlayer): CommandHandler<SeekToSecond> {
    override fun handle(command: SeekToSecond) {
        if(!devicePlayer.isReady()){
            val queue = queueRepository.get()
            when(val song = queue.currentSong()){
                is Some -> {
                    devicePlayer.changeSong(song.t.location){
                        devicePlayer.seekTo(command.second)
                    }
                }
            }
        }else{
            devicePlayer.seekTo(command.second)
        }
    }
}

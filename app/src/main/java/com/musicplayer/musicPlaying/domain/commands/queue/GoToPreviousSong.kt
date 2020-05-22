package com.musicplayer.musicPlaying.domain.commands.queue

import arrow.core.Some
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.commands.player.IDevicePlayer

class GoToPreviousSong : Command
class GoToPreviousSongHandler(private val repository: QueueRepository, private val devicePlayer: IDevicePlayer): CommandHandler<GoToPreviousSong>
{
    override fun handle(command: GoToPreviousSong) {
        val queue = repository.get()

        queue.goToPrev()

        repository.save(queue)

        when(val song = queue.currentSong()){
            is Some -> devicePlayer.changeSong(song.t.location){
                if(devicePlayer.isPlaying()){
                    devicePlayer.play()
                }
            }
        }
    }
}
package com.musicplayer.musicPlaying.domain.commands.queue

import arrow.core.Some
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.commands.player.IDevicePlayer

class GoToNextSong : Command
class GotToNextSongHandler(private val repository: QueueRepository, private val devicePlayer: IDevicePlayer):CommandHandler<GoToNextSong>
{
    override fun handle(command: GoToNextSong) {
        val queue = repository.get()

        queue.goToNext()
        
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
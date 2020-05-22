package com.musicplayer.musicPlaying.domain.commands.queue

import arrow.core.Some
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.commands.player.IDevicePlayer

data class GoToSong(val songPosition:Int) : Command
class GoToSongHandler(private val repository: QueueRepository, private val devicePlayer: IDevicePlayer): CommandHandler<GoToSong>
{
    override fun handle(command: GoToSong) {
        val queue = repository.get()

        queue.goTo(command.songPosition)

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
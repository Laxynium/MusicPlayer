package com.musicplayer.musicPlaying.domain.commands.queue

import arrow.core.Either
import arrow.core.Right
import arrow.core.Some
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.commands.player.IDevicePlayer

data class GoToSong(val songPosition:Int) : Command
class GoToSongHandler(private val repository: QueueRepository, private val devicePlayer: IDevicePlayer): CommandHandler<GoToSong>
{
    override suspend fun handle(command: GoToSong): Either<Error, Unit> {
        val queue = repository.get()

        queue.goTo(command.songPosition)

        repository.save(queue)

        when(val song = queue.currentSong()){
            is Some -> {
                devicePlayer.changeSong(song.t.location)
                if(devicePlayer.isPlaying()){
                    devicePlayer.play()
                }
            }
        }
        return Right(Unit)
    }
}
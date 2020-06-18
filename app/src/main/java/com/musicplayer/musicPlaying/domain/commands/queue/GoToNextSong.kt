package com.musicplayer.musicPlaying.domain.commands.queue

import arrow.core.Either
import arrow.core.Right
import arrow.core.Some
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.commands.player.IDevicePlayer

class GoToNextSong : Command
class GoToNextSongHandler(private val repository: QueueRepository, private val devicePlayer: IDevicePlayer):CommandHandler<GoToNextSong>
{
    override suspend fun handle(command: GoToNextSong): Either<Error, Unit> {
        val queue = repository.get()

        queue.goToNext()
        
        repository.save(queue)

        when (val song = queue.currentSong()) {
            is Some -> {
                devicePlayer.changeSong(song.t.location)
            }
        }
        return Right(Unit)
    }
}
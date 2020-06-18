package com.musicplayer.musicPlaying.domain.commands.queue

import arrow.core.Either
import arrow.core.Right
import arrow.core.Some
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.commands.player.IDevicePlayer

class GoToPreviousSong : Command
class GoToPreviousSongHandler(private val repository: QueueRepository, private val devicePlayer: IDevicePlayer): CommandHandler<GoToPreviousSong>
{
    override suspend fun handle(command: GoToPreviousSong): Either<Error, Unit> {
        val queue = repository.get()

        queue.goToPrev()

        repository.save(queue)

        when(val song = queue.currentSong()){
            is Some -> {
                devicePlayer.changeSong(song.t.location)
            }
        }
        return Right(Unit)
    }
}
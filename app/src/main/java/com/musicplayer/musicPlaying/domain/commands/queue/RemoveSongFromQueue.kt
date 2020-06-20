package com.musicplayer.musicPlaying.domain.commands.queue

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.commands.player.IDevicePlayer
import java.util.*


data class RemoveSongFromQueue(val songId: UUID, val position:Int? = null): Command
class RemoveSongFromQueueHandler(private val repository: QueueRepository, private val devicePlayer: IDevicePlayer): CommandHandler<RemoveSongFromQueue>
{
    override suspend fun handle(command: RemoveSongFromQueue): Either<Error, Unit> {
        val queue = repository.get()

        val removalResult = queue.removeSong(command.songId, command.position)

        if(removalResult.first){
            devicePlayer.handleSongRemoval(nextSongLocation = queue.currentSong().orNull()?.location)
        }

        repository.save(queue)

        return Right(Unit)
    }
}
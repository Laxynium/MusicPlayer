package com.musicplayer.musicPlaying.domain.commands.queue

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.Song
import java.util.*

data class EnqueueSongAsNext(val songId:UUID, val songLocation:String): Command
class EnqueueSongAsNextHandler(private val repository: QueueRepository):CommandHandler<EnqueueSongAsNext>
{
    override suspend fun handle(command: EnqueueSongAsNext): Either<Error, Unit> {
        val queue = repository.get()

        queue.enqueueAsNext(
            Song(
                command.songId,
                command.songLocation
            )
        )
        
        repository.save(queue)

        return Right(Unit)
    }
}
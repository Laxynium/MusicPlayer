package com.musicplayer.musicPlaying.domain.commands.queue

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.Song
import java.util.*

data class EnqueueSong(val songId:UUID, val songLocation: String): Command
class EnqueueSongHandler(private val repository: QueueRepository):CommandHandler<EnqueueSong>
{
    override suspend fun handle(command: EnqueueSong): Either<Error, Unit> {
        val queue = repository.get()

        queue.enqueue(
            Song(
                command.songId,
                command.songLocation
            )
        )

        repository.save(queue)

        return Right(Unit)
    }
}
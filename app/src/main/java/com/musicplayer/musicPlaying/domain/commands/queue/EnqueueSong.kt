package com.musicplayer.musicPlaying.domain.commands.queue

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.Song
import java.util.*

data class EnqueueSong(val songId:UUID, val songLocation: String): Command
class EnqueueSongHandler(private val repository: QueueRepository):CommandHandler<EnqueueSong>
{
    override fun handle(command: EnqueueSong) {
        val queue = repository.get()

        queue.enqueue(
            Song(
                command.songId,
                command.songLocation
            )
        )

        repository.save(queue)
    }
}
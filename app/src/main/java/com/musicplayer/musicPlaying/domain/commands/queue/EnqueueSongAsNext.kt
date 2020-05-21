package com.musicplayer.musicPlaying.domain.commands.queue

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.Song
import java.util.*

data class EnqueueSongAsNext(val songId:UUID, val songLocation:String): Command
class EnqueueSongAsNextHandler(private val repository: QueueRepository):CommandHandler<EnqueueSongAsNext>
{
    override fun handle(command: EnqueueSongAsNext) {
        val queue = repository.get()

        queue.enqueueAsNext(
            Song(
                command.songId,
                command.songLocation
            )
        )
        
        repository.save(queue)
    }
}
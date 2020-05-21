package com.musicplayer.musicPlaying.domain.commands.queue

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicPlaying.domain.QueueRepository

class GoToNextSong : Command
class GotToNextSongHandler(private val repository: QueueRepository):CommandHandler<GoToNextSong>
{
    override fun handle(command: GoToNextSong) {
        val queue = repository.get()

        queue.goToNext()
        
        repository.save(queue)
    }
}
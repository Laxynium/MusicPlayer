package com.musicplayer.musicPlaying.domain.commands.queue

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicPlaying.domain.QueueRepository

class GoToPreviousSong : Command
class GoToPreviousSongHandler(private val repository: QueueRepository): CommandHandler<GoToPreviousSong>
{
    override fun handle(command: GoToPreviousSong) {
        val queue = repository.get()

        queue.goToPrev()

        repository.save(queue)
    }
}
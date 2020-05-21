package com.musicplayer.musicPlaying.domain.commands.queue

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicPlaying.domain.QueueRepository

data class GoToSong(val songPosition:Int) : Command
class GoToSongHandler(private val repository: QueueRepository): CommandHandler<GoToSong>
{
    override fun handle(command: GoToSong) {
        val queue = repository.get()

        queue.goTo(command.songPosition)

        repository.save(queue)
    }
}
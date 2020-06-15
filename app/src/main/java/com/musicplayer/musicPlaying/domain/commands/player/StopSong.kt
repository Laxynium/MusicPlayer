package com.musicplayer.musicPlaying.domain.commands.player

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler

class StopSong : Command
class StopSongHandler: CommandHandler<StopSong> {
    override suspend fun handle(command: StopSong) {
        TODO("Not yet implemented")
    }
}
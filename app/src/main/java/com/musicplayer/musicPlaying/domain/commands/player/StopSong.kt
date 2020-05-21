package com.musicplayer.musicPlaying.domain.commands.player

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler

class StopSong : Command
class StopSongHandler: CommandHandler<StopSong> {
    override fun handle(command: StopSong) {
        TODO("Not yet implemented")
    }
}
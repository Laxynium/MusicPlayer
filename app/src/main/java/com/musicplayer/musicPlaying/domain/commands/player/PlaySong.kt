package com.musicplayer.musicPlaying.domain.commands.player

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler

class PlaySong : Command
class PlaySongHandler: CommandHandler<PlaySong>{
    override fun handle(command: PlaySong) {
        //call play song from android
    }
}


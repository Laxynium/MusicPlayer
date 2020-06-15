package com.musicplayer.musicPlaying.domain.commands.player

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler

class SeekToSecond : Command
class SeekToSecondHandler: CommandHandler<SeekToSecond> {
    override suspend fun handle(command: SeekToSecond) {
        //call play song from android
    }
}

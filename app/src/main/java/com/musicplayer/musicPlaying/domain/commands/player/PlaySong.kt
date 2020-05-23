package com.musicplayer.musicPlaying.domain.commands.player

import arrow.core.Either
import arrow.core.Right
import arrow.core.Some
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicPlaying.domain.QueueRepository

class PlaySong : Command
class PlaySongHandler(private val queueRepository: QueueRepository, private val devicePlayer: IDevicePlayer): CommandHandler<PlaySong>{
    override suspend fun handle(command: PlaySong): Either<Error, Unit> {
        if(!devicePlayer.isReady()){
            val queue = queueRepository.get()
            when(val song = queue.currentSong()){
                is Some -> {
                    devicePlayer.changeSong(song.t.location)
                    devicePlayer.play()
                }
            }
        }else{
            devicePlayer.play()
        }
        return Right(Unit)
    }
}


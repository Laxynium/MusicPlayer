package com.musicplayer.musicPlaying.domain.commands.player

import arrow.core.Either
import arrow.core.Right
import arrow.core.Some
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicPlaying.domain.QueueRepository

class PauseSong : Command
class PauseSongHandler(private val queueRepository: QueueRepository, private val devicePlayer: IDevicePlayer): CommandHandler<PauseSong> {
    override suspend fun handle(command: PauseSong):Either<Error,Unit> {
        if(!devicePlayer.isReady()){
            val queue = queueRepository.get()
            when(val song = queue.currentSong()){
                is Some -> {
                    devicePlayer.changeSong(song.t.location)
                    devicePlayer.pause()
                }
            }
        }else{
            devicePlayer.pause()
        }
        return Right(Unit)
    }
}
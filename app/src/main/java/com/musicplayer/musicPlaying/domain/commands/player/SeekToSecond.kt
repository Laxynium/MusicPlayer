package com.musicplayer.musicPlaying.domain.commands.player

import arrow.core.Either
import arrow.core.Right
import arrow.core.Some
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicPlaying.domain.QueueRepository

data class SeekToSecond(val second:Int) : Command
class SeekToSecondHandler(private val queueRepository: QueueRepository, private val devicePlayer: IDevicePlayer): CommandHandler<SeekToSecond> {
    override suspend fun handle(command: SeekToSecond): Either<Error, Unit> {
        if(!devicePlayer.isReady()){
            val queue = queueRepository.get()
            when(val song = queue.currentSong()){
                is Some -> {
                    devicePlayer.changeSong(song.t.location)
                    devicePlayer.seekTo(command.second)
                }
            }
        }else{
            devicePlayer.seekTo(command.second)
        }
        return Right(Unit)
    }
}

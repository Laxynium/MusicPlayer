package com.musicplayer.musicManagement.mainPlaylist

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import java.util.*

data class RemoveSongFromMainPlaylist(
    val songId: UUID
) : Command

class RemoveSongFromMainPlaylistHandler :
    CommandHandler<RemoveSongFromMainPlaylist> {
    override suspend fun handle(command: RemoveSongFromMainPlaylist): Either<com.musicplayer.framework.messaging.Error, Unit> {
        return Right(Unit)
    }
}
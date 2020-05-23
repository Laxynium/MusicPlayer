package com.musicplayer.musicManagement.regularPlaylist

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import java.util.*

data class RenameRegularPlaylist(
    val playlistId: UUID,
    val newName: String
) : Command

class RenameRegularPlaylistHandler() :
    CommandHandler<RenameRegularPlaylist> {
    override suspend fun handle(command: RenameRegularPlaylist) : Either<Error, Unit> {
        return Right(Unit)
    }
}
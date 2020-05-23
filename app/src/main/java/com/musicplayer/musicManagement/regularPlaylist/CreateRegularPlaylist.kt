package com.musicplayer.musicManagement.regularPlaylist

import arrow.core.Either
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import java.util.*

data class CreateRegularPlaylist(
    val playlistId: UUID,
    val playlistName: String
) : Command

class CreateRegularPlaylistHandler() :
    CommandHandler<CreateRegularPlaylist> {
    override suspend fun handle(command: CreateRegularPlaylist): Either<Error, Unit> {
        return Either.right(Unit)
    }
}
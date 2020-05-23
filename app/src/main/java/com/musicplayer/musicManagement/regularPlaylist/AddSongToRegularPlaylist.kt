package com.musicplayer.musicManagement.regularPlaylist

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import java.util.*

data class AddSongToRegularPlaylist(
    val playlistId: UUID,
    val songId: UUID
) : Command

class AddSongToRegularPlaylistHandler :
    CommandHandler<AddSongToRegularPlaylist> {
    override suspend fun handle(command: AddSongToRegularPlaylist): Either<Error, Unit> {
        return Right(Unit)
    }
}
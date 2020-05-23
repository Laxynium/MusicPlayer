package com.musicplayer.musicManagement.regularPlaylist

import arrow.core.Either
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import java.util.*

data class RemoveSongFromRegularPlaylist(
    val playlistId: UUID,
    val songId: UUID
) : Command

class RemoveSongFromRegularPlaylistHandler :
    CommandHandler<RemoveSongFromRegularPlaylist> {
    override suspend fun handle(command: RemoveSongFromRegularPlaylist) : Either<Error, Unit> {
        return Either.right(Unit)
    }
}
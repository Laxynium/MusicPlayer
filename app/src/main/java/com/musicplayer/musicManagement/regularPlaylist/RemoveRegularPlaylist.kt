package com.musicplayer.musicManagement.regularPlaylist

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

data class RemoveRegularPlaylist(
    val playlistId: UUID
) : Command

class RemoveRegularPlaylistHandler(private val playlistRepository: PlaylistRepository) :
    CommandHandler<RemoveRegularPlaylist> {
    override suspend fun handle(command: RemoveRegularPlaylist): Either<Error, Unit> {
        if (!command.playlistId.equals(UUID.fromString("00000000-0000-0000-0000-000000000001")))
            playlistRepository.remove(command.playlistId)
        return Right(Unit)
    }
}
package com.musicplayer.musicManagement.regularPlaylist

import arrow.core.Either
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import com.musicplayer.framework.messaging.Error
import java.util.*

data class RemoveRegularPlaylist(
    val playlistId: UUID
) : Command

class RemoveRegularPlaylistHandler(private val playlistRepository: PlaylistRepository) :
    CommandHandler<RemoveRegularPlaylist> {
    override suspend fun handle(command: RemoveRegularPlaylist): Either<Error, Unit> {
        playlistRepository.remove(command.playlistId)
        return Right(Unit)
    }
}
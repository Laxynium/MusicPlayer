package com.musicplayer.musicManagement.regularPlaylist

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import com.musicplayer.framework.messaging.Error
import java.util.*

data class RenameRegularPlaylist(
    val playlistId: UUID,
    val newName: String
) : Command

class RenameRegularPlaylistHandler(private val playlistRepository: PlaylistRepository) :
    CommandHandler<RenameRegularPlaylist> {
    override suspend fun handle(command: RenameRegularPlaylist): Either<Error, Unit> {
        playlistRepository.save(
            playlistRepository.get(command.playlistId)
                .apply { name = command.newName })

        return Right(Unit)
    }
}
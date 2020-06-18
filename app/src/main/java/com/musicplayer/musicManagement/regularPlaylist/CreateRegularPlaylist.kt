package com.musicplayer.musicManagement.regularPlaylist

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import com.musicplayer.framework.messaging.Error
import java.util.*

data class CreateRegularPlaylist(
    val playlistId: UUID,
    val playlistName: String
) : Command

class CreateRegularPlaylistHandler(private val playlistRepository: PlaylistRepository) :
    CommandHandler<CreateRegularPlaylist> {
    override suspend fun handle(command: CreateRegularPlaylist): Either<Error, Unit>{
        playlistRepository.insert(Playlist(command.playlistId, command.playlistName))
        return Right(Unit)
    }
}
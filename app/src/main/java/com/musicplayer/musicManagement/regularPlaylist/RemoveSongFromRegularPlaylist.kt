package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.database.musicManagement.PlaylistDao
import arrow.core.Either
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import com.musicplayer.framework.messaging.Error
import java.util.*

data class RemoveSongFromRegularPlaylist(
    val playlistId: UUID,
    val songId: UUID
) : Command

class RemoveSongFromRegularPlaylistHandler(private val playlistDao: PlaylistDao) :
    CommandHandler<RemoveSongFromRegularPlaylist> {
    override suspend fun handle(command: RemoveSongFromRegularPlaylist): Either<Error, Unit> {
        playlistDao.removeRef(command.playlistId, command.songId)
        return Right(Unit)
    }
}
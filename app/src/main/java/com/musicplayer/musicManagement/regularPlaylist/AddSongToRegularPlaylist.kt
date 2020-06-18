package com.musicplayer.musicManagement.regularPlaylist

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.database.musicManagement.PlaylistDao
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import java.util.*

class AddSongToRegularPlaylist(
    val playlistId: UUID,
    val songId: UUID
) : Command

class AddSongToRegularPlaylistHandler(private val playlistDao: PlaylistDao) :
    CommandHandler<AddSongToRegularPlaylist> {
    override suspend fun handle(command: AddSongToRegularPlaylist): Either<Error, Unit> {
        playlistDao.addRef(command.playlistId, command.songId)
        return Right(Unit)
    }
}
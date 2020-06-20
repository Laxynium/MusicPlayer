package com.musicplayer.musicManagement.mainPlaylist

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.database.musicManagement.PlaylistDao
import com.musicplayer.database.musicManagement.SongDao
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import java.util.*

data class RemoveSongFromMainPlaylist(
    val songId: UUID
) : Command

class RemoveSongFromMainPlaylistHandler(
    private val playlistDao: PlaylistDao,
    private val songDao: SongDao
)
    : CommandHandler<RemoveSongFromMainPlaylist> {
    override suspend fun handle(command: RemoveSongFromMainPlaylist): Either<Error, Unit> {
        playlistDao.deleteSong(songDao.get(command.songId))

        return Right(Unit)
//        TODO("Should also remove from memory")
    }
}
package com.musicplayer.musicManagement.mainPlaylist

import android.net.Uri
import arrow.core.Either
import arrow.core.Right
import com.musicplayer.database.musicManagement.PlaylistDao
import com.musicplayer.database.musicManagement.SongDao
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.musicManagement.mainPlaylist.services.SongsFileManager
import com.musicplayer.musicPlaying.domain.commands.queue.RemoveSongFromQueue
import java.util.*

data class RemoveSongFromMainPlaylist(
    val songId: UUID,
    val location: String?
) : Command

class RemoveSongFromMainPlaylistHandler(
    private val playlistDao: PlaylistDao,
    private val songDao: SongDao,
    private val messageBus: MessageBus,
    private val songsFileManager: SongsFileManager
)
    : CommandHandler<RemoveSongFromMainPlaylist> {
    override suspend fun handle(command: RemoveSongFromMainPlaylist): Either<Error, Unit> {
        playlistDao.deleteSong(songDao.get(command.songId))

        messageBus.dispatch(RemoveSongFromQueue(command.songId))

        songsFileManager.delete(Uri.parse(command.location!!))
        return Right(Unit)
    }
}
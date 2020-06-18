package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.database.musicManagement.PlaylistDao
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

data class RemoveSongFromRegularPlaylist(
    val playlistId: UUID,
    val songId: UUID
) : Command

class RemoveSongFromRegularPlaylistHandler(private val playlistDao: PlaylistDao) :
    CommandHandler<RemoveSongFromRegularPlaylist> {
    override suspend fun handle(command: RemoveSongFromRegularPlaylist) {
        playlistDao.removeRef(command.playlistId, command.songId)
    }
}
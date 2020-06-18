package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.database.musicManagement.PlaylistDao
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import com.musicplayer.musicManagement.repositories.SongRepository
import java.util.*

class AddSongToRegularPlaylist(
    val playlistId: UUID,
    val songId: UUID
) : Command

class AddSongToRegularPlaylistHandler(private val playlistDao: PlaylistDao) :
    CommandHandler<AddSongToRegularPlaylist> {
    override suspend fun handle(command: AddSongToRegularPlaylist) {
        playlistDao.addRef(command.playlistId, command.songId)
    }
}
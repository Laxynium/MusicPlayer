package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

data class CreateRegularPlaylist(
    val playlistId: UUID,
    val playlistName: String
) : Command

class CreateRegularPlaylistHandler(private val playlistRepository: PlaylistRepository) :
    CommandHandler<CreateRegularPlaylist> {
    override suspend fun handle(command: CreateRegularPlaylist) {
        playlistRepository.insert(Playlist(command.playlistId, command.playlistName))
    }
}
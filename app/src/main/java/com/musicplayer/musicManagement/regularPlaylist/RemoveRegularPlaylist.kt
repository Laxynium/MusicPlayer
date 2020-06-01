package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

data class RemoveRegularPlaylist(
    val playlistId: UUID
) : Command

class RemoveRegularPlaylistHandler(private val playlistRepository: PlaylistRepository) :
    CommandHandler<RemoveRegularPlaylist> {
    override fun handle(command: RemoveRegularPlaylist) {
        playlistRepository.remove(command.playlistId)
    }
}
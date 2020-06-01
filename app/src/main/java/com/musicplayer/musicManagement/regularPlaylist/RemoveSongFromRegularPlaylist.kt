package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

data class RemoveSongFromRegularPlaylist(
    val playlistId: UUID,
    val songId: UUID
) : Command

class RemoveSongFromRegularPlaylistHandler(private val playlistRepository: PlaylistRepository) :
    CommandHandler<RemoveSongFromRegularPlaylist> {
    override fun handle(command: RemoveSongFromRegularPlaylist) {
        playlistRepository.save(
            playlistRepository.get(command.playlistId)
                .apply { songs.filter { s -> s.songId != command.songId } })
    }
}
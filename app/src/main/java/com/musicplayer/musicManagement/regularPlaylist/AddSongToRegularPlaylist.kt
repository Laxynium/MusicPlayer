package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import com.musicplayer.musicManagement.repositories.SongRepository
import java.util.*

data class AddSongToRegularPlaylist(
    val playlistId: UUID,
    val songId: UUID
) : Command

class AddSongToRegularPlaylistHandler(private val playlistRepository: PlaylistRepository, private val songRepository: SongRepository) :
    CommandHandler<AddSongToRegularPlaylist> {
    override fun handle(command: AddSongToRegularPlaylist) {
        playlistRepository.save(
            playlistRepository.get(command.playlistId)
                .apply { songs.plus(songRepository.get(command.songId)) })

    }
}
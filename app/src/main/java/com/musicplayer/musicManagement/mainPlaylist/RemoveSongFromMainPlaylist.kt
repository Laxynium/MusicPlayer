package com.musicplayer.musicManagement.mainPlaylist

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

data class RemoveSongFromMainPlaylist(
    val songId: UUID
) : Command

class RemoveSongFromMainPlaylistHandler(private val playlistRepository: PlaylistRepository) :
    CommandHandler<RemoveSongFromMainPlaylist> {
    override fun handle(command: RemoveSongFromMainPlaylist) {
        playlistRepository.save(
            playlistRepository.getMain()
                .apply { songs.filter { song -> song.songId != command.songId } })

//        TODO("Should also remove from memory")
    }
}
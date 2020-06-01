package com.musicplayer.musicManagement.mainPlaylist

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

data class AddSongFromYoutube(
    val songId: UUID,
    val ytId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String
) : Command

class AddSongFromYoutubeHandler(private val playlistRepository: PlaylistRepository) :
    CommandHandler<AddSongFromYoutube> {
    override fun handle(command: AddSongFromYoutube) {
        TODO("save song into")
//        playlistRepository.get(0)
    }
}


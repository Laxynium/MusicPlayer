package com.musicplayer.musicManagement.mainPlaylist

import com.musicplayer.framework.Command
import com.musicplayer.framework.CommandHandler
import java.util.*

data class AddSongFromYoutube(
    val songId: UUID,
    val ytId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String
) : Command

class AddSongFromYoutubeHandler() : CommandHandler<AddSongFromYoutube> {
    override fun handle(command: AddSongFromYoutube) {

    }
}


package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.Command
import com.musicplayer.framework.CommandHandler
import java.util.*

data class CreateRegularPlaylist(
    val playlistId: UUID,
    val playlistName: String
) : Command

class CreateRegularPlaylistHandler() :
    CommandHandler<CreateRegularPlaylist> {
    override fun handle(command: CreateRegularPlaylist) {

    }
}
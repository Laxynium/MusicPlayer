package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
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
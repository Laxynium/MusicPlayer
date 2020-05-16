package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import java.util.*

data class RemoveRegularPlaylist(
    val playlistId: UUID
) : Command

class RemoveRegularPlaylistHandler() :
    CommandHandler<RemoveRegularPlaylist> {
    override fun handle(command: RemoveRegularPlaylist) {

    }
}
package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import java.util.*

data class RenameRegularPlaylist(
    val playlistId: UUID,
    val newName: String
) : Command

class RenameRegularPlaylistHandler() :
    CommandHandler<RenameRegularPlaylist> {
    override fun handle(command: RenameRegularPlaylist) {

    }
}
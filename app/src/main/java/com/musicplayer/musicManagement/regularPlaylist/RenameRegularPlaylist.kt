package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.Command
import com.musicplayer.framework.CommandHandler
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
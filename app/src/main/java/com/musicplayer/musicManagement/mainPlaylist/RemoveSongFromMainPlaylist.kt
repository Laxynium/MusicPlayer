package com.musicplayer.musicManagement.mainPlaylist

import com.musicplayer.framework.Command
import com.musicplayer.framework.CommandHandler
import java.util.*

data class RemoveSongFromMainPlaylist(
    val songId: UUID
) : Command

class RemoveSongFromMainPlaylistHandler() :
    CommandHandler<RemoveSongFromMainPlaylist> {
    override fun handle(command: RemoveSongFromMainPlaylist) {

    }
}
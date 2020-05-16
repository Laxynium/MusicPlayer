package com.musicplayer.musicManagement.mainPlaylist

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import java.util.*

data class RemoveSongFromMainPlaylist(
    val songId: UUID
) : Command

class RemoveSongFromMainPlaylistHandler :
    CommandHandler<RemoveSongFromMainPlaylist> {
    override fun handle(command: RemoveSongFromMainPlaylist) {

    }
}
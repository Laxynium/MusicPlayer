package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.Command
import com.musicplayer.framework.CommandHandler
import java.util.*

data class RemoveSongFromRegularPlaylist(
    val playlistId: UUID,
    val songId: UUID
) : Command

class RemoveSongFromRegularPlaylistHandler() :
    CommandHandler<RemoveSongFromRegularPlaylist> {
    override fun handle(command: RemoveSongFromRegularPlaylist) {

    }
}
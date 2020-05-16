package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.Command
import com.musicplayer.framework.CommandHandler
import java.util.*

data class AddSongToRegularPlaylist(
    val playlistId: UUID,
    val songId: UUID
) : Command

class AddSongToRegularPlaylistHandler() :
    CommandHandler<AddSongToRegularPlaylist> {
    override fun handle(command: AddSongToRegularPlaylist) {

    }
}
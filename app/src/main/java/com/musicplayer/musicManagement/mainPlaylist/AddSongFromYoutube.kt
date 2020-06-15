package com.musicplayer.musicManagement.mainPlaylist

import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

data class AddSongFromYoutube(
    val songId: UUID,
    val ytId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String
) : Command

class AddSongFromYoutubeHandler(private val playlistRepository: PlaylistRepository) :
    CommandHandler<AddSongFromYoutube> {
    override suspend fun handle(command: AddSongFromYoutube) {
        val location = null
//        TODO("^^^ save song into sth")
        var playlist = playlistRepository.getMain()
        var song = Song(command.songId, command.ytId, command.title, command.artist, command.thumbnailUrl, location)
        playlist.songs.plus(song)
        playlistRepository.save(playlist)

    }
}


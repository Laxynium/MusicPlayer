package com.musicplayer.musicManagement.mainPlaylist

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicManagement.mainPlaylist.services.SongsFileManager
import com.musicplayer.musicManagement.mainPlaylist.services.YoutubeService
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*


data class AddSongFromYoutube(
    val ytId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String
) : Command

class AddSongFromYoutubeHandler(private val playlistRepository: PlaylistRepository,
                                private val youtubeService: YoutubeService,
                                private val songsFileManager: SongsFileManager)
    : CommandHandler<AddSongFromYoutube> {
    override suspend fun handle(command: AddSongFromYoutube): Either<Error,Unit> {
        val result = youtubeService.downloadSong(command.ytId)
        if(result.isLeft()){
            return result.map {}
        }
        val songDownload = result.toOption().orNull()!!

        val uri = songsFileManager.save(command.title, songDownload.data.toByteArray())

        val location = uri?.toString()
        val playlist = playlistRepository.getMain()
        val song = Song(UUID.randomUUID(), command.ytId, command.title, command.artist, command.thumbnailUrl, location)
        playlist.songs = playlist.songs.plus(song)
        playlistRepository.save(playlist)

        return Right(Unit)
    }
}

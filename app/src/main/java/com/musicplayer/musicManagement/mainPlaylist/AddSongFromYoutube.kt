package com.musicplayer.musicManagement.mainPlaylist

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicManagement.mainPlaylist.services.MemoryCache
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
        if(MemoryCache.exits(command.ytId)){
            return Left(Error("song_is_being_downloading","Song is being downloaded"))
        }

        val playlist = playlistRepository.getMain()
        if(playlist.songs.any { it.ytId == command.ytId }){
            return Left(Error("already_downloaded","Song is already downloaded"))
        }

        MemoryCache.put(command.ytId,true)
        val result = youtubeService.downloadSong(command.ytId)
        if(result.isLeft()){
            return result.map {}
        }
        val songDownload = result.toOption().orNull()!!

        val uri = songsFileManager.save(command.title, songDownload.data.toByteArray())

        val location = uri?.toString()

        val song = Song(UUID.randomUUID(), command.ytId, command.title, command.artist, command.thumbnailUrl, location)
        playlist.songs = playlist.songs.plus(song)

        playlistRepository.save(playlist)

        MemoryCache.clear(command.ytId)
        return Right(Unit)
    }
}

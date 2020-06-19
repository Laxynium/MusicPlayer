package com.musicplayer.musicManagement.mainPlaylist

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicManagement.mainPlaylist.services.YoutubeService
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*


data class AddSongFromYoutube(
    val ytId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String
) : Command

class AddSongFromYoutubeHandler(private val playlistRepository: PlaylistRepository,
                                private val youtubeService: YoutubeService,
                                private val context: Context)
    : CommandHandler<AddSongFromYoutube> {
    override suspend fun handle(command: AddSongFromYoutube): Either<Error,Unit> {
        val result = youtubeService.downloadSong(command.ytId)
        if(result.isLeft()){
            return result.map {}
        }
        val songDownload = result.toOption().orNull()!!

        val collection = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)

        val values = ContentValues().apply {
            put(MediaStore.Audio.Media.DISPLAY_NAME, command.title)
            put(MediaStore.Audio.Media.RELATIVE_PATH, "Music/my_songs")
            put(MediaStore.Audio.Media.MIME_TYPE, "audio/mp3")
            put(MediaStore.Audio.Media.IS_PENDING, 1)
        }
        val resolver = context.contentResolver
        val uri = resolver.insert(collection,values)
        uri?.let {
            withContext(Dispatchers.IO){
                resolver.openOutputStream(uri)?.use { outputStream ->
                    outputStream.write(songDownload.data.toByteArray())
                }
            }

            values.clear()
            values.put(MediaStore.Audio.Media.IS_PENDING, 0)
            resolver.update(uri,values,null,null)
        }

        val location = uri?.toString()
        val playlist = playlistRepository.getMain()
        val song = Song(UUID.randomUUID(), command.ytId, command.title, command.artist, command.thumbnailUrl, location)
        playlist.songs = playlist.songs.plus(song)
        playlistRepository.save(playlist)

        return Right(Unit)
    }
}


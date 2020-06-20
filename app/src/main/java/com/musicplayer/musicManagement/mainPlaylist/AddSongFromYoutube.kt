package com.musicplayer.musicManagement.mainPlaylist

import android.content.ContentValues
import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import arrow.core.Either
import arrow.core.Right
import com.musicplayer.BuildConfig
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicManagement.mainPlaylist.services.SongDownload
import com.musicplayer.musicManagement.mainPlaylist.services.YoutubeService
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.*


data class AddSongFromYoutube(
    val ytId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String
) : Command

private const val AUTHORITY = "${BuildConfig.APPLICATION_ID}.provider"

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

        val uri = saveFile(command.title, songDownload)

        val location = uri?.toString()
        val playlist = playlistRepository.getMain()
        val song = Song(UUID.randomUUID(), command.ytId, command.title, command.artist, command.thumbnailUrl, location)
        playlist.songs = playlist.songs.plus(song)
        playlistRepository.save(playlist)

        return Right(Unit)
    }

    private suspend fun saveFile(title: String,
                                 songDownload: SongDownload) : Uri?{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            saveFileQ(title, songDownload)
        else
            saveFileLegacy(title, songDownload)
    }

    private suspend fun saveFileQ(
        title: String,
        songDownload: SongDownload
    ): Uri? {
        val collection = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)

        val values = ContentValues().apply {
            put(MediaStore.Audio.Media.DISPLAY_NAME, "${title}.mp3")
            put(MediaStore.Audio.Media.RELATIVE_PATH, "Music/my_songs")
            put(MediaStore.Audio.Media.MIME_TYPE, "audio/mp3")
            put(MediaStore.Audio.Media.IS_PENDING, 1)
        }
        val resolver = context.contentResolver
        val uri = resolver.insert(collection, values)
        uri?.let {
            withContext(Dispatchers.IO) {
                resolver.openOutputStream(uri)?.use { outputStream ->
                    outputStream.write(songDownload.data.toByteArray())
                }
            }

            values.clear()
            values.put(MediaStore.Audio.Media.IS_PENDING, 0)
            resolver.update(uri, values, null, null)
        }
        return uri
    }

    private suspend fun saveFileLegacy(title:String, songDownload: SongDownload): Uri? =
        withContext(Dispatchers.IO) {
            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath, "${title}.mp3")
            file.outputStream().write(songDownload.data.toByteArray())
            Uri.fromFile(file)
        }
}

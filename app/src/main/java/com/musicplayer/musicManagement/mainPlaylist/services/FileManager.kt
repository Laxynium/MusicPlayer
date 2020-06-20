package com.musicplayer.musicManagement.mainPlaylist.services

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class SongsFileManager(private val context: Context){
    suspend fun save(title: String, data:ByteArray): Uri? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            saveFileQ(title, data)
        else
            saveFileLegacy(title, data)
    }

    suspend fun delete(uri:Uri) = withContext(Dispatchers.IO){
        uri.path.let{
            return@let File(it!!)
        }.let {
            if(it.exists()){
                it.delete()
            }
        }
    }

    private suspend fun saveFileQ(
        title: String,
        data: ByteArray
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
                    outputStream.write(data)
                }
            }

            values.clear()
            values.put(MediaStore.Audio.Media.IS_PENDING, 0)
            resolver.update(uri, values, null, null)
        }
        return uri
    }

    private suspend fun saveFileLegacy(title:String, data: ByteArray): Uri? =
        withContext(Dispatchers.IO) {
            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath, "${title}.mp3")
            file.outputStream().write(data)
            Uri.fromFile(file)
        }
}
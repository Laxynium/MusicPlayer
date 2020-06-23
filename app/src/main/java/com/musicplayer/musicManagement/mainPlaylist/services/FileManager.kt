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
            saveFileQ(normalize(title), data)
        else
            saveFileLegacy(normalize(title), data)
    }

    suspend fun delete(uri:Uri) = withContext(Dispatchers.IO){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            context.contentResolver.delete(uri,null,null)
        }else{
            val file = File(uri.path)
            file.delete()
            if (file.exists()) {
                file.canonicalFile.delete()
                if (file.exists()) {
                    context.applicationContext
                        .deleteFile(file.name)
                }else{

                }
            }else{

            }
        }
    }

    private fun normalize(title:String):String{
        return title.toLowerCase().replace(" ","_").replace(":","_");
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
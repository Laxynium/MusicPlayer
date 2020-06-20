package com.musicplayer.musicManagement.mainPlaylist.services

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.github.kittinunf.fuel.coroutines.awaitByteArrayResponseResult
import com.github.kittinunf.fuel.httpDownload
import com.musicplayer.framework.messaging.Error
import java.io.ByteArrayOutputStream

data class SongDownload(val ytId: String, val data:List<Byte>)
class YoutubeService(private val downloadLinkGenerator: YtMp3DownloadLinkGenerator){
    suspend fun downloadSong(ytId:String): Either<Error, SongDownload>{
        val downloadLink = downloadLinkGenerator.generateLink(ytId)
        val outputStream = ByteArrayOutputStream()
        println(downloadLink)
        val (_, _, result) = downloadLink.httpDownload()
            .streamDestination{response, request ->
                Pair(outputStream,{ByteArray(0).inputStream()})
            }
            .progress { readBytes, totalBytes ->
                val progress = readBytes.toFloat() / totalBytes.toFloat() * 100
//                println("Bytes downloaded $readBytes / $totalBytes ($progress %)")
            }
            .appendHeader("User-Agent", "curl/7.55.1")
           .awaitByteArrayResponseResult()


        return result.fold({ data ->
             Right(SongDownload(ytId, outputStream.toByteArray().toList()))
        },{ error ->
            Left(Error("download_error",error.message?:""))
        })
    }
}


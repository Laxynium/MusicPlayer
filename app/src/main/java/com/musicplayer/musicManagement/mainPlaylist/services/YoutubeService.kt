package com.musicplayer.musicManagement.mainPlaylist.services


class YoutubeService(private val downloadLinkGenerator: YtMp3DownloadLinkGenerator){
    suspend fun downloadSong(ytId:String){
        val downloadLink = downloadLinkGenerator.generateLink(ytId)
        println(downloadLink)
    }
}


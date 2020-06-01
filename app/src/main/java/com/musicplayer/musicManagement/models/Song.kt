package com.musicplayer.musicManagement.models

import com.musicplayer.database.musicManagement.PlaylistEntity
import com.musicplayer.database.musicManagement.SongEntity
import com.musicplayer.musicPlaying.domain.Song
import java.util.*

open class Song {
    val songId: UUID
    val ytId: String
    val title: String
    val artist: String
    val thumbnailUrl: String
    var location: String? = null



    constructor(entity: SongEntity) {
        songId = entity.songId
        ytId = entity.ytId
        title = entity.title
        artist = entity.artist
        thumbnailUrl = entity.thumbnailUrl
        location = entity.location
    }

    constructor(songId: UUID, ytId: String, title: String, artist: String, thumbnailUrl: String, location: String?) {
        this.songId = songId
        this.ytId = ytId
        this.title = title
        this.artist = artist
        this.thumbnailUrl = thumbnailUrl
        if(location.isNullOrEmpty()) this.location = location
    }

    fun toEntity(): SongEntity {
        return SongEntity(songId, ytId, title, artist, thumbnailUrl, location)
    }

}
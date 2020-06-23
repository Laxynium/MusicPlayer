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
        if(!location.isNullOrEmpty()) this.location = location
    }

    fun toEntity(): SongEntity {
        return SongEntity(songId, ytId, title, artist, thumbnailUrl, location)
    }

    fun getLabel(): String {
        return "$title - $artist"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as com.musicplayer.musicManagement.models.Song

        if (songId != other.songId) return false
        if (ytId != other.ytId) return false
        if (title != other.title) return false
        if (artist != other.artist) return false
        if (thumbnailUrl != other.thumbnailUrl) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = songId.hashCode()
        result = 31 * result + ytId.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + artist.hashCode()
        result = 31 * result + thumbnailUrl.hashCode()
        result = 31 * result + (location?.hashCode() ?: 0)
        return result
    }


}
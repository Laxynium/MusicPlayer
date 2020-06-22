package com.musicplayer.musicManagement.models

import com.musicplayer.database.musicManagement.PlaylistEntity
import com.musicplayer.database.musicManagement.PlaylistWithSongsEntity
import com.musicplayer.musicManagement.models.Song
import java.util.*

open class Playlist {
    var playlistId: UUID
    var name: String
    var songs: List<Song> = mutableListOf()


    constructor(playlistId: UUID, name: String) {
        this.playlistId = playlistId
        this.name = name
    }

    constructor(entity: PlaylistEntity) {
        playlistId = entity.playlistId
        name = entity.name
    }

    constructor(entity: PlaylistWithSongsEntity) {
        playlistId = entity.playlist.playlistId
        name = entity.playlist.name
        songs = entity.songs.map { t -> Song(t) }
    }

    fun toEntity(): PlaylistEntity {
        return PlaylistEntity(playlistId, name)
    }
}
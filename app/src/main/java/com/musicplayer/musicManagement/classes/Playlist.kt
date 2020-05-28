package com.musicplayer.musicManagement.classes

import com.musicplayer.database.musicManagement.PlaylistEntity

open class Playlist {

    private constructor(entity: PlaylistEntity) : this(entity) {
        this.currentSongIndex = state.currentSongIndex
        state.songs.forEach {
            this.songs.add(it)
        }
    }
}
package com.musicplayer.musicManagement.repositories

import com.musicplayer.musicManagement.models.Playlist
import java.util.*

interface PlaylistRepository {
    fun get(id: UUID) : Playlist
    fun getMain(): Playlist
    fun save(playlist: Playlist)
}
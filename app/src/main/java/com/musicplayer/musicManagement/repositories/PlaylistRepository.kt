package com.musicplayer.musicManagement.repositories

import androidx.lifecycle.LiveData
import com.musicplayer.musicManagement.models.Playlist
import java.util.*

interface PlaylistRepository {
    fun get(id: UUID) : Playlist
    fun getMain(): Playlist
    fun get(): List<Playlist>
    fun insert(playlist: Playlist)
    fun save(playlist: Playlist)
    fun remove(id: UUID)
}
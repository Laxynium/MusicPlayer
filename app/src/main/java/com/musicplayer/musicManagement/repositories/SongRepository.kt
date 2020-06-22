package com.musicplayer.musicManagement.repositories

import com.musicplayer.musicManagement.models.Song
import java.util.*

interface SongRepository {
    fun get(id: UUID) : Song
    fun getByTitle(title: String): Song
    fun get() : List<Song>
    fun save(song: Song)
}
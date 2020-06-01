package com.musicplayer.database.musicManagement

import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

class PlaylistDatabaseRepository(private val playlistDao: PlaylistDao) :
    PlaylistRepository {
    override fun get(id: UUID): Playlist {
        return Playlist(playlistDao.get(id))
    }

    override fun getMain(): Playlist {
        return Playlist(playlistDao.getMain())
    }

    override fun save(playlist: Playlist){
        playlistDao.update(playlist.toEntity())
    }
}
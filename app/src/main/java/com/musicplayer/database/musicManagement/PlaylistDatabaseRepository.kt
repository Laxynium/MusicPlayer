package com.musicplayer.database.musicManagement

import androidx.lifecycle.LiveData
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

class PlaylistDatabaseRepository(private val playlistDao: PlaylistDao) :
    PlaylistRepository {
    override fun get(id: UUID): Playlist {
        return Playlist(playlistDao.get(id))
    }

    override fun get(): List<Playlist> {
        return playlistDao.getWithSongs().map { t -> Playlist(t) }
    }

    override fun getMain(): Playlist {
        return Playlist(playlistDao.getMain())
    }

    override fun remove(id: UUID) {
        playlistDao.delete(get(id).toEntity())
    }

    override fun save(playlist: Playlist){
        val entity = PlaylistWithSongsEntity(playlist.toEntity(), playlist.songs.map { it.toEntity() })
        playlistDao.save(entity)
    }
}
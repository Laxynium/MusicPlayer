package com.musicplayer.database.musicManagement

import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.repositories.SongRepository
import java.util.*

class SongDatabaseRepository(private val songDao: SongDao):
    SongRepository {
    override fun get(id: UUID): Song {
        return Song(songDao.get(id))
    }

    override fun getByTitle(title: String): Song {
        return Song(
            songDao.getByTitle(
                title
            )
        )
    }

    override fun get(): List<Song> {
        return songDao.get().map { t ->
            Song(
                t
            )
        }
    }

    override fun save(song: Song) {
        songDao.update(song.toEntity())
    }
}
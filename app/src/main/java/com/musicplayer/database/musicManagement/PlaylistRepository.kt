package com.musicplayer.database.musicManagement

import com.musicplayer.musicManagement.classes.Playlist
import com.musicplayer.musicPlaying.domain.Queue
import com.musicplayer.musicPlaying.domain.Queue.Creator.fromState
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.Song
import java.util.*

class PlaylistRepository(private val playlistDao: PlaylistDao) {
    override fun get(id: UUID): Playlist{
        return playlistDao.get(id)
    }

    override fun save(queue: Queue){
        val state = queue.getState()
        val entity = QueueWithSongsEntity(
            QueueEntity(1,state.currentSongIndex),
            state.songs.mapIndexed { index, song -> SongEntity(song.id.toString(), 1, song.location, index) })
        queueDao.save(entity)
    }
}
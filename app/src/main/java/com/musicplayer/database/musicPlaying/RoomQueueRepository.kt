package com.musicplayer.database.musicPlaying

import com.musicplayer.musicPlaying.domain.Queue
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.Song
import java.util.*

class RoomQueueRepository(private val queueDao: QueueDao) : QueueRepository {
    override fun get(): Queue{
        val queueWithSongsEntity = queueDao.get()
        return Queue.fromState(Queue.QueueState(queueWithSongsEntity.queue.currentSongIndex, queueWithSongsEntity.songs.sortedBy {
            it.position
        }.map {
            Song(UUID.fromString(it.id), it.location)
        }))
    }

    override fun save(queue: Queue){
        val state = queue.getState()
        val entity = QueueWithSongsEntity(
            QueueEntity(1,state.currentSongIndex),
            state.songs.mapIndexed { index, song -> SongEntity(song.id.toString(), 1, song.location, index) })
        queueDao.save(entity)
    }
}
package com.musicplayer.database.musicPlaying

import com.musicplayer.musicPlaying.domain.Queue
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.Song

class RoomQueueRepository(private val queueDao: QueueWriteDao) : QueueRepository {
    override fun get(): Queue{
        val queueWithSongsEntity = queueDao.get()
        return Queue.fromState(Queue.QueueState(queueWithSongsEntity.queue.currentSongIndex, queueWithSongsEntity.songs.sortedBy {
            it.position
        }.map {
            Song(it.id, it.location)
        }))
    }

    override fun save(queue: Queue){
        val state = queue.getState()
        val entity = QueueWithSongsEntity(
            QueueEntity(1,state.currentSongIndex),
            state.songs.mapIndexed { index, song -> SongEntity(song.id, 1, song.location, index) })
        queueDao.save(entity)
    }
}
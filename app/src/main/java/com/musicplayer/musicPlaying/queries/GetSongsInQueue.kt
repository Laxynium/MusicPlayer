package com.musicplayer.musicPlaying.queries

import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicPlaying.domain.QueueRepository
import java.util.*

data class SongDto(val songId:UUID, val songLocation:String, val isCurrent:Boolean)
class GetSongsInQueue : Query<Collection<SongDto>>
class GetSongsInQueueHandler(private val queueRepository: QueueRepository): QueryHandler<GetSongsInQueue, Collection<SongDto>> {
    override suspend fun handle(query: GetSongsInQueue): Collection<SongDto> {
        val state = queueRepository.get().getState()
        return state.songs.mapIndexed { index, song ->  SongDto(
            song.id,
            song.location,
            index == state.currentSongIndex
        )}
    }
}
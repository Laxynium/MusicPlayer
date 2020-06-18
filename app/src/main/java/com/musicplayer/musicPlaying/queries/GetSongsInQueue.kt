package com.musicplayer.musicPlaying.queries

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.musicplayer.database.musicPlaying.QueueReadDao
import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import java.util.*

data class SongDto(val songId:UUID, val songLocation:String, val position: Int,val isCurrent:Boolean)
class GetSongsInQueue : Query<LiveData<List<SongDto>>>
class GetSongsInQueueHandler(private val dao: QueueReadDao): QueryHandler<GetSongsInQueue, LiveData<List<SongDto>>> {
    override suspend fun handle(query: GetSongsInQueue): LiveData<List<SongDto>> {
        val songs = dao.getObservable()
        return Transformations.map(songs) {
            it.songs
                .sortedBy { s -> s.position }
                .mapIndexed { i, s ->
                    SongDto(UUID.fromString(s.id), s.location, i,it.queue.currentSongIndex == i)
                }
        }
    }
}
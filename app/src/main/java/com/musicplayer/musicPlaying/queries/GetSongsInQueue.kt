package com.musicplayer.musicPlaying.queries

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.musicplayer.database.musicPlaying.QueueReadDao
import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import java.util.*

data class SongDto(val songId:UUID, val position:Int, val title:String, val thumbnail: String, val songLocation:String, val isCurrent:Boolean)
class GetSongsInQueue : Query<LiveData<List<SongDto>>>
class GetSongsInQueueHandler(private val dao: QueueReadDao): QueryHandler<GetSongsInQueue, LiveData<List<SongDto>>> {
    override suspend fun handle(query: GetSongsInQueue): LiveData<List<SongDto>> {
        val queueSongs = dao.getQueueSongs()
        return Transformations.map(queueSongs) { songs ->
            songs.map { SongDto(it.songId, it.position, it.title, it.thumbnailUrl, it.location, it.isCurrent) }
        }
    }
}
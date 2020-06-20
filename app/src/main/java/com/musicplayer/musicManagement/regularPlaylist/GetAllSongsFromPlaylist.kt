package com.musicplayer.musicManagement.regularPlaylist

import androidx.lifecycle.LiveData
import com.musicplayer.database.musicManagement.PlaylistReadDao
import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicManagement.models.Song
import java.util.*

class GetAllSongsFromPlaylist(val playlistId: UUID) : Query<LiveData<List<Song>>>
class GetAllSongsFromPlaylistHandler(private val playlistDao: PlaylistReadDao) :
    QueryHandler<GetAllSongsFromPlaylist, LiveData<List<Song>>> {
    override suspend fun handle(query: GetAllSongsFromPlaylist): LiveData<List<Song>> {
        return playlistDao.getObservableSongs(query.playlistId)

    }
}
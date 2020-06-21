package com.musicplayer.musicManagement.regularPlaylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.musicplayer.database.musicManagement.PlaylistReadDao
import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicManagement.models.Playlist

class GetAllRegularPlaylists : Query<LiveData<List<Playlist>>>
class GetAllRegularPlaylistsHandler(private val playlistDao: PlaylistReadDao) :
    QueryHandler<GetAllRegularPlaylists, LiveData<List<Playlist>>> {
    override suspend fun handle(query: GetAllRegularPlaylists): LiveData<List<Playlist>> {
        val playlists = playlistDao.getRegularObservable()
        return Transformations.map(playlists) {
            it.map { t -> Playlist(t) }
        }
    }
}
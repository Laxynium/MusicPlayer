package com.musicplayer.musicManagement.regularPlaylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.musicplayer.database.musicManagement.PlaylistReadDao
import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicManagement.models.Playlist

class GetAllPlaylists : Query<LiveData<List<Playlist>>>
class GetAllPlaylistsHandler(private val playlistDao: PlaylistReadDao) :
    QueryHandler<GetAllPlaylists, LiveData<List<Playlist>>> {
    override suspend fun handle(query: GetAllPlaylists): LiveData<List<Playlist>> {
        val playlists = playlistDao.getObservable()
        return Transformations.map(playlists) {
            it.map { t -> Playlist(t) }
        }
    }
}
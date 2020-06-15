package com.musicplayer.musicManagement.regularPlaylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.musicplayer.database.musicManagement.PlaylistDao
import com.musicplayer.database.musicManagement.PlaylistReadDao
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import com.musicplayer.musicPlaying.queries.GetSongsInQueue
import com.musicplayer.musicPlaying.queries.SongDto
import java.util.*

//data class PlaylistDto(val playlistId: UUID,
//                       val name: String)
class GetAllPlaylists : Query<LiveData<List<Playlist>>>
class GetAllPlaylistsHandler(private val playlistDao: PlaylistReadDao): QueryHandler<GetAllPlaylists, LiveData<List<Playlist>>> {
    override suspend fun handle(query: GetAllPlaylists) : LiveData<List<Playlist>> {
        val playlists = playlistDao.getObservable()
        return Transformations.map(playlists) {
            it.map { t -> Playlist(t) }
        }
    }
}
package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

class GetAllSongsFromPlaylistNotLiveData(val playlistId: UUID) : Query<List<Song>>
class GetAllSongsFromPlaylistNotLiveDataHandler(private val playlistRepository: PlaylistRepository) :
    QueryHandler<GetAllSongsFromPlaylistNotLiveData, List<Song>> {
    override suspend fun handle(query: GetAllSongsFromPlaylistNotLiveData): List<Song> {
        return playlistRepository.get(query.playlistId).songs
    }
}
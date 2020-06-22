package com.musicplayer.musicManagement.regularPlaylist

import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import java.util.*

data class GetPlaylistById(val playlistId: UUID) : Query<Playlist>

class GetPlaylistByIdHandler(private val playlistRepository: PlaylistRepository) :
    QueryHandler<GetPlaylistById, Playlist> {
    override suspend fun handle(query: GetPlaylistById): Playlist {
        return playlistRepository.get(query.playlistId)
    }
}
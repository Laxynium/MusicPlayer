package com.musicplayer.musicManagement.regularPlaylist

import androidx.lifecycle.LiveData
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import com.musicplayer.musicPlaying.queries.GetSongsInQueue
import com.musicplayer.musicPlaying.queries.SongDto
import java.util.*
data class GetPlaylistById(val playlistId: UUID) : Query<Playlist>

class GetPlaylistByIdHandler(private val playlistRepository: PlaylistRepository) :
    QueryHandler<GetPlaylistById, Playlist> {
    override suspend fun handle(query: GetPlaylistById) : Playlist {
        return playlistRepository.get(query.playlistId)
    }
}
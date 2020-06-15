package com.musicplayer.musicManagement.regularPlaylist

import androidx.lifecycle.LiveData
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.repositories.PlaylistRepository
import com.musicplayer.musicPlaying.queries.GetSongsInQueue
import com.musicplayer.musicPlaying.queries.SongDto
import java.util.*
data class GetAllSongsFromPlaylist(val playlistId: UUID) : Query<List<Song>>

class GetAllSongsFromPlaylistHandler(private val playlistRepository: PlaylistRepository) :
    QueryHandler<GetAllSongsFromPlaylist, List<Song>> {
    override suspend fun handle(query: GetAllSongsFromPlaylist) : List<Song> {
        return playlistRepository.get(query.playlistId).songs
    }
}
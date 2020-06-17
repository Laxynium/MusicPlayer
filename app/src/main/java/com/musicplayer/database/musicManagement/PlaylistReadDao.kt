package com.musicplayer.database.musicManagement

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.musicplayer.musicManagement.models.Song
import java.util.*

@Dao
interface PlaylistReadDao {
    @Query("SELECT * FROM playlists")
    fun getObservable(): LiveData<List<PlaylistWithSongsEntity>>

    @Query("SELECT s.songId, s.title, s.artist, s.location, s.thumbnailUrl, s.ytId FROM songs s, playlistsongcrossref cr WHERE s.songId = cr.songId AND cr.playlistId LIKE :id")
    fun getObservableSongs(id: UUID): LiveData<List<Song>>
}
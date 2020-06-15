package com.musicplayer.database.musicManagement

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PlaylistReadDao {
    @Query("SELECT * FROM playlists")
    fun getObservable(): LiveData<List<PlaylistWithSongsEntity>>
}
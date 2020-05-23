package com.musicplayer.database.musicManagement

import androidx.room.*

@Dao
interface PlaylistDao {
    @Transaction
    @Query("SELECT * from playlists where playlistId like :id ")
    fun get(id: Int): PlaylistEntity

    @Transaction
    @Query("SELECT * FROM playlists")
    fun get(): List<PlaylistWithSongsEntity>

    @Update
    fun update(playlist: PlaylistEntity)

    @Insert
    fun insert(playlist: PlaylistEntity)

    @Delete
    fun delete(playlist: PlaylistEntity)

}
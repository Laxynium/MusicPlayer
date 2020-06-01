package com.musicplayer.database.musicManagement

import androidx.room.*
import java.util.*

@Dao
interface PlaylistDao {
    @Transaction
    @Query("SELECT * from playlists where playlistId like :id ")
    fun get(id: UUID): PlaylistEntity

    @Transaction
    @Query("SELECT * FROM playlists")
    fun get(): List<PlaylistEntity>

    @Transaction
    @Query("SELECT * FROM playlists WHERE name LIKE 'Main Playlist'")
    fun getMain(): PlaylistEntity

    @Update
    fun update(playlist: PlaylistEntity)

    @Insert
    fun insert(playlist: PlaylistEntity)

    @Delete
    fun delete(playlist: PlaylistEntity)

}
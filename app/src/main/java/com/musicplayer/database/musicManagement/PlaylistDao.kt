package com.musicplayer.database.musicManagement

import androidx.room.*
import com.musicplayer.database.musicPlaying.QueueWithSongsEntity
import java.util.*

@Dao
interface PlaylistDao {
    @Transaction
    @Query("SELECT * from playlists where playlistId like :id ")
    fun get(id: UUID): PlaylistWithSongsEntity

    @Transaction
    @Query("SELECT * from playlists")
    fun getWithSongs(): List<PlaylistWithSongsEntity>

    @Transaction
    @Query("SELECT * from playlists where name like :name")
    fun getByNameWithSongs(name: String): PlaylistWithSongsEntity

    @Transaction
    @Query("SELECT * FROM playlists")
    fun get(): List<PlaylistEntity>

    @Transaction
    @Query("SELECT * FROM playlists WHERE name LIKE 'Main Playlist'")
    fun getMain(): PlaylistEntity

    @Transaction
    fun save(playlist: PlaylistWithSongsEntity){
        update(playlist.playlist)
        playlist.songs.forEach {
            deleteSongFromPlaylist(it)
        }
        playlist.songs.forEach {
            insertSongIntoPlaylist(it)
        }
    }

    @Update
    fun update(playlist: PlaylistEntity)

    @Insert
    fun insert(playlist: PlaylistEntity)

    @Delete
    fun delete(playlist: PlaylistEntity)

    @Delete
    fun deleteSongFromPlaylist(song: SongEntity)

    @Insert
    fun insertSongIntoPlaylist(song: SongEntity)

}
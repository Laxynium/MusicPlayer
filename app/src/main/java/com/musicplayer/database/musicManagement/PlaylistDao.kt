package com.musicplayer.database.musicManagement

import androidx.room.*
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
    @Query("SELECT * FROM playlists WHERE playlistId LIKE '00000000-0000-0000-0000-000000000001'")
    fun getMain(): PlaylistWithSongsEntity

    @Transaction
    fun save(playlist: PlaylistWithSongsEntity){
        update(playlist.playlist)
//        playlist.songs.forEach {
//            deleteSong(it)
//        }
        playlist.songs.forEach {
            insertSong(it)
            insert(PlaylistSongCrossRef(playlist.playlist.playlistId, it.songId))
        }
    }

    @Transaction
    @Query("DELETE FROM PlaylistSongCrossRef WHERE playlistId = :playlistId AND songId = :songId")
    fun removeRef(playlistId: UUID, songId: UUID)

    @Transaction
    @Query("INSERT INTO PlaylistSongCrossRef (playlistId, songId) Values (:playlistId, :songId)")
    fun addRef(playlistId: UUID, songId: UUID)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(crossRef: PlaylistSongCrossRef)

    @Update
    fun update(playlist: PlaylistEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(playlist: PlaylistEntity)

    @Delete
    fun delete(playlist: PlaylistEntity)

    @Delete
    fun deleteSong(song: SongEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSong(song: SongEntity)

}
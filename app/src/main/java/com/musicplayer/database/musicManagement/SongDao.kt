package com.musicplayer.database.musicManagement

import androidx.room.*
import java.util.*

@Dao
interface SongDao {
    @Transaction
    @Query("SELECT * from songs where songId like :id ")
    fun get(id: UUID): SongEntity

    @Transaction
    @Query("SELECT * FROM songs")
    fun get(): List<SongEntity>

    @Transaction
    @Query("SELECT * FROM songs WHERE title LIKE :name")
    fun getByTitle(name: String): SongEntity

    @Update
    fun update(song: SongEntity)

    @Insert
    fun insert(song: SongEntity)

    @Delete
    fun delete(song: SongEntity)
}
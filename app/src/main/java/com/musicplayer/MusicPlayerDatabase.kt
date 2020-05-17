package com.musicplayer

import androidx.room.*

@Entity(tableName = "songs")
data class Song(
    @PrimaryKey var id:String,
    var title:String
)

@Dao
interface SongDao{
    @Query("SELECT * FROM songs WHERE id = :id")
    suspend fun getSong(id:String):Song
}

@Database(entities = [Song::class], version = 1, exportSchema = false)
abstract class MusicPlayerDatabase: RoomDatabase(){
    abstract fun songDao():SongDao
}
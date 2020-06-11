package com.musicplayer.database.musicPlaying

import androidx.room.*

@Dao
interface QueueWriteDao{

    @Transaction
    @Query("SELECT * FROM queue limit 1")
    fun get():QueueWithSongsEntity

    @Update
    fun updateQueue(queue: QueueEntity)

    @Insert
    fun insertSong(songEntity: SongEntity)

    @Delete
    fun deleteSong(songEntity: SongEntity)

    @Transaction
    fun save(queue: QueueWithSongsEntity){
        updateQueue(queue.queue)
        queue.songs.forEach {
            deleteSong(it)
        }
        queue.songs.forEach {
            insertSong(it)
        }
    }
}
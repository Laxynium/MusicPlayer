package com.musicplayer.database.musicPlaying

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QueueDao{

    @Transaction
    @Query("SELECT * FROM queue limit 1")
    fun get():QueueWithSongsEntity

    @Query("SELECT * FROM queue limit 1")
    fun getObservable():LiveData<QueueWithSongsEntity>

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
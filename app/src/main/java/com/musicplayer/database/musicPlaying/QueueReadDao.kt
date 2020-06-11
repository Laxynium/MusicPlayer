package com.musicplayer.database.musicPlaying

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QueueReadDao{
    @Query("SELECT * FROM queue limit 1")
    fun getObservable():LiveData<QueueWithSongsEntity>
}
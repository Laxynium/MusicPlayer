package com.musicPlaying

import java.util.*

interface QueueSongsExamples {
    data class QueueSong(val id:UUID, val location:String)
    val song1 get() = create(1)
    val song2 get() = create(2)
    val song3 get() = create(3)
    val song4 get() = create(4)
    val song5 get() = create(5)
    private fun create(nr:Int) = QueueSong(
        UUID.fromString("00000000-0000-0000-0000-00000000000${nr}"),
        "/location/${nr}"
    )
}
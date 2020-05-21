package com.musicplayer.musicPlaying.domain

interface QueueRepository {
    fun get():Queue
    fun save(queue: Queue)
}
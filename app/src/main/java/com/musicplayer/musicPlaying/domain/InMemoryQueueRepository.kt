package com.musicplayer.musicPlaying.domain

class InMemoryQueueRepository: QueueRepository{
    private val map:MutableMap<Int, Queue> = mutableMapOf(Pair(1,Queue()))
    override fun get(): Queue {
        return map[1]!!
    }

    override fun save(queue: Queue) {
        map[1] = queue
    }
}
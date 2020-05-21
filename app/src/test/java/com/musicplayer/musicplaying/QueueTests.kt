package com.musicplayer.musicplaying

import arrow.core.Option
import arrow.core.Some
import com.musicplayer.musicPlaying.domain.*
import com.musicplayer.musicPlaying.domain.Queue
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be true`
import org.amshove.kluent.`should contain same`
import org.junit.Test
import java.util.*

class QueueTests {
    private val testCaseSongs = listOf(
        createSong(1),
        createSong(2),
        createSong(3),
        createSong(4)
    )

    private fun createSong(nr:Int)= Song(UUID.fromString("00000000-0000-0000-0000-00000000000${nr}"),"not_relevant")

    @Test
    fun `enqueued song on empty queue is current song`(){
        val sut = Queue()
        val song = createSong(1)
        sut.enqueue(song)
        sut.currentSong() `should be equal to` Some(song)
    }

    @Test
    fun `enqueued song is on the last position of queue`() {
        val testCases = listOf(
            listOf(createSong(1)),
            listOf(
                createSong(1),
                createSong(2)),
            listOf(
                createSong(1),
                createSong(2),
                createSong(3),
                createSong(4))
        )
        testCases.forEach { it ->
            val sut = Queue()
            it.forEach {
                sut.enqueue(it)
            }

            val asList = sut.asList()

            asList `should contain same` it
            sut.currentSong() `should be equal to` Some(it.first())
        }
    }

    @Test
    fun `current song is none when queue is empty`(){
        val sut = Queue()

        val currentSong = sut.currentSong()

        currentSong `should be equal to` Option.empty()
    }

    @Test
    fun `goes to next song when current song is not last one is queue`(){
        val sut = Queue()
        testCaseSongs.forEach{
                sut.enqueue(it)
            }

        sut.goToNext()

        sut.currentSong() `should be equal to` Some(testCaseSongs[1])
    }

    @Test
    fun `goes to first song when current song is the last one in queue`(){
        val sut = Queue()
        testCaseSongs.forEach{
            sut.enqueue(it)
        }
        sut.goToNext()
        sut.goToNext()
        sut.goToNext()

        sut.goToNext()

        sut.currentSong() `should be equal to` Some(testCaseSongs[0])
    }

    @Test
    fun `current song is none after going to next one when queue is empty`(){
        val sut = Queue()

        sut.goToNext()

        sut.currentSong() `should be equal to` Option.empty()
    }

    @Test
    fun `goes to previous song when current song is not first one`(){
        val sut = Queue()
        testCaseSongs
            .forEach{
                sut.enqueue(it)
            }
        sut.goToNext()
        sut.goToNext()
        sut.goToNext()

        sut.goToPrev()

        sut.currentSong() `should be equal to` Some(testCaseSongs[2])
    }

    @Test
    fun `goes to last song when current song is the first one in queue`(){
        val sut = Queue()
        testCaseSongs
            .forEach{
                sut.enqueue(it)
            }

        sut.goToPrev()

        sut.currentSong() `should be equal to` Some(testCaseSongs[3])
    }

    @Test
    fun `current song is none after going to previous one when queue is empty`(){
        val sut = Queue()

        sut.goToPrev()

        sut.currentSong() `should be equal to` Option.empty()
    }

    @Test
    fun `goes to given song position`(){
        val sut = Queue()
        testCaseSongs
            .forEach{
                sut.enqueue(it)
            }

        val result = sut.goTo(2)

        result.isRight().`should be true`()
        sut.currentSong() `should be equal to` Some(testCaseSongs[2])
    }

    @Test
    fun `going to song postion out of range returns error`(){
        val testCaseSongs = listOf(
            createSong(1),
            createSong(2),
            createSong(4)
        )
        val sut = Queue()
        testCaseSongs
            .forEach{
                sut.enqueue(it)
            }

        val result = sut.goTo(3)

        result.isLeft().`should be true`()
        sut.currentSong() `should be equal to` Some(testCaseSongs[0])
    }

    @Test
    fun `enqueuing as next song correctly updates queue`(){
        val sut = Queue()
        testCaseSongs
            .forEach{
                sut.enqueue(it)
            }
        sut.goToNext()
        sut.goToNext()

        sut.enqueueAsNext(createSong(5))

        sut.asList() `should contain same` listOf(
            createSong(1),
            createSong(2),
            createSong(3),
            createSong(5),
            createSong(4)
        )
        sut.currentSong() `should be equal to` Some(            createSong(3))
    }

    @Test
    fun `enqueuing as next song correctly updates queue when current song is last one`(){
        val testCaseSongs = listOf(
            createSong(1),
            createSong(2)
        )
        val sut = Queue()
        testCaseSongs
            .forEach{
                sut.enqueue(it)
            }
        sut.goToNext()

        sut.enqueueAsNext(createSong(3))

        sut.asList() `should contain same` listOf(
            createSong(1),
            createSong(2),
            createSong(3)
        )
        sut.currentSong() `should be equal to` Some(createSong(2))
    }
}

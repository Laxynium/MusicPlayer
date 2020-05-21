package com.musicPlaying

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.SetupDb
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.musicPlaying.domain.commands.queue.*
import com.musicplayer.musicPlaying.queries.GetSongsInQueue
import com.musicplayer.musicPlaying.queries.SongDto
import org.amshove.kluent.shouldContainSame
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
@MediumTest
class MusicPlayingIntegrationTest : KoinTest, SetupDb,
    QueueSongsExamples {
    private val messageBus: MessageBus by inject()

    @Test
    fun verify() {
        messageBus.dispatch(
            EnqueueSong(
                song1.id,
                song1.location
            )
        )
        messageBus.dispatch(
            EnqueueSong(
                song2.id,
                song2.location
            )
        )
        messageBus.dispatch(
            EnqueueSong(
                song3.id,
                song3.location
            )
        )

        messageBus.dispatch(GoToNextSong())

        var songs = messageBus.dispatch(GetSongsInQueue())
        songs?.shouldContainSame(
            listOf(
                SongDto(song1.id, song1.location, false),
                SongDto(song2.id, song2.location, true),
                SongDto(song3.id, song3.location, false)
            ))

        messageBus.dispatch(
            EnqueueSongAsNext(
                song4.id,
                song4.location
            )
        )

        songs = messageBus.dispatch(GetSongsInQueue())
        songs?.shouldContainSame(
            listOf(
                SongDto(song1.id, song1.location, false),
                SongDto(song2.id, song2.location, true),
                SongDto(song4.id, song4.location, false),
                SongDto(song3.id, song3.location, false)
            ))

        messageBus.dispatch(GoToNextSong())
        messageBus.dispatch(GoToNextSong())
        messageBus.dispatch(GoToPreviousSong())

        songs = messageBus.dispatch(GetSongsInQueue())
        songs?.shouldContainSame(
            listOf(
                SongDto(song1.id, song1.location, false),
                SongDto(song2.id, song2.location, false),
                SongDto(song4.id, song4.location, true),
                SongDto(song3.id, song3.location, false)
            ))

        messageBus.dispatch(
            GoToSong(
                0
            )
        )

        songs = messageBus.dispatch(GetSongsInQueue())
        songs?.shouldContainSame(
            listOf(
                SongDto(song1.id, song1.location, true),
                SongDto(song2.id, song2.location, false),
                SongDto(song4.id, song4.location, false),
                SongDto(song3.id, song3.location, false)
            ))
    }
}

package com.musicplayer.musicPlaying.domain.commands.queue

import arrow.core.Either
import arrow.core.Right
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import com.musicplayer.musicPlaying.domain.QueueRepository
import com.musicplayer.musicPlaying.domain.Song
import java.util.*

data class PlaylistSongDto(val songId: UUID, val songLocation: String)
data class EnqueuePlaylist(val songs: List<PlaylistSongDto>): Command
class EnqueuePlaylistHandler(private val repository: QueueRepository):CommandHandler<EnqueuePlaylist>
{
    override suspend fun handle(command: EnqueuePlaylist): Either<Error, Unit> {
        val queue = repository.get()

        val songs = command.songs.map { Song(it.songId, it.songLocation) }
        queue.enqueuePlaylist(songs)

        repository.save(queue)

        return Right(Unit)
    }
}
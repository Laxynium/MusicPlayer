package com.musicplayer.musicManagement.mainPlaylist

import arrow.core.Either
import com.musicplayer.framework.messaging.Command
import com.musicplayer.framework.messaging.CommandHandler
import com.musicplayer.framework.messaging.Error
import java.util.*

data class AddSongFromYoutube(
    val songId: UUID,
    val ytId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String
) : Command

class AddSongFromYoutubeHandler :
    CommandHandler<AddSongFromYoutube> {
    override suspend fun handle(command: AddSongFromYoutube): Either<Error, Unit> {

        return Either.right(Unit)
    }
}


package com.musicplayer.musicPlaying.queries

import androidx.lifecycle.LiveData
import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicPlaying.domain.commands.player.IDevicePlayer

class GetSongProgress : Query<LiveData<Int>>
class GetSongProgressHandler(private val devicePlayer: IDevicePlayer)
    : QueryHandler<GetSongProgress, LiveData<Int>>
{
    override suspend fun handle(query: GetSongProgress): LiveData<Int> {
        return devicePlayer.currentPosition()
    }
}
package com.musicplayer.musicPlaying.queries

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import com.musicplayer.musicPlaying.domain.commands.player.IDevicePlayer

data class PlayingStatus(var isPlaying: Boolean)
class GetPlayingStatus : Query<LiveData<PlayingStatus>>
class GetPlayingStatusHandler(private val devicePlayer: IDevicePlayer):
    QueryHandler<GetPlayingStatus, LiveData<PlayingStatus>> {
    override suspend fun handle(query: GetPlayingStatus): LiveData<PlayingStatus> {
        val isPlaying = devicePlayer.isPlaying()
        return isPlaying.map {
            PlayingStatus(it)
        }
    }
}
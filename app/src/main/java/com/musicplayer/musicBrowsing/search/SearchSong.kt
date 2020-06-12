package com.musicplayer.musicBrowsing.search

import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler

data class SongDto(val youtubeId:String, val title:String, val thumbnailUrl:String)

data class SearchSong(val songTitle:String): Query<Collection<SongDto>>

class SearchSongQueryHandler: QueryHandler<SearchSong, Collection<SongDto>> {
    override suspend fun handle(query: SearchSong): Collection<SongDto> {
        return listOf()
    }
}
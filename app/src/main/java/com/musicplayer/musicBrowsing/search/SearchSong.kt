package com.musicplayer.musicBrowsing.search

import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler

data class SearchSong(val songTitle:String):
    Query<Collection<SongDto>>
class SearchSongQueryHandler:
    QueryHandler<SearchSong, Collection<SongDto>> {
    override fun handle(query: SearchSong): Collection<SongDto> {
        return listOf()
    }

}
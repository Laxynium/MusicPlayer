package com.musicplayer.musicBrowsing.search.domain

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.coroutines.awaitObjectResponseResult
import com.github.kittinunf.fuel.httpGet
import com.musicplayer.framework.messaging.Query
import com.musicplayer.framework.messaging.QueryHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class SongDto(val youtubeId:String, val title:String, val thumbnailUrl:String)

data class SearchSong(val songTitle:String): Query<Collection<SongDto>>

class SearchSongQueryHandler: QueryHandler<SearchSong, Collection<SongDto>> {

    object SongDtoDeserializer : ResponseDeserializable<Collection<SongDto>> {
        override fun deserialize(content: String):Collection<SongDto> {
            //TODO Here deserialize the response body. Use soap, to extract proper fields from json
            return listOf(
                SongDto(
                    "FWDXwrgdm9w",
                    "Kwiat Jabłoni - \"Dziś późno pójdę spać\"",
                    "abc"
                )
            )
        }
    }

    override suspend fun handle(query: SearchSong): Collection<SongDto> = withContext(Dispatchers.IO){
        val url = "https://www.youtube.com/results?search_query=${query.songTitle}&pbj=1"
        val (_, _,result) = url.httpGet()
            .appendHeader("User-Agent", "curl/7.55.1")
            .appendHeader("Connection", "keep-alive")
            .appendHeader("X-YouTube-Client-Name", "1")
            .appendHeader("X-YouTube-Client-Version", "2.20190626")
            .appendHeader(
                "Cookie",
                "VISITOR_INFO1_LIVE=cIdr9kyAxzo; YSC=uiQyEuiTVYU; PREF=f1=50000000; GPS=1; CONSENT=WP.27b70d; ST-13r0l79=oq=nadchodzi%20lato&gs_l=youtube.3..0i71k1l10.0.0.1.431792.0.0.0.0.0.0.0.0..0.0....0...1ac..64.youtube..0.0.0....0.HCy0eUqhZUA&feature=web-masthead-search&itct=CB4Q7VAiEwjau5D-oorjAhWOOZsKHX10AT0o9CQ%3D&csn=bAgVXdrVCI7z7AT96IXoAw"
            )
            .appendHeader("Accept", "*/*")
            .awaitObjectResponseResult(SongDtoDeserializer)

        var returnValue: Collection<SongDto> = listOf()
        result.fold(
            { data ->
                println(data)
                returnValue = data
            },
            { error ->
                println("${error.exception}, ${error.message}")
            }
        )
        returnValue
    }
}


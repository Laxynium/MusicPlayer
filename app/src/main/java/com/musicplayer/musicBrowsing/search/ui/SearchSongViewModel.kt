package com.musicplayer.musicBrowsing.search.ui

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.kittinunf.fuel.httpGet
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicBrowsing.domain.SongRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchSongViewModel(private val messageBus: MessageBus): ObservableViewModel() {

    @Bindable
    val records = MutableLiveData<List<SongRecord>>()

    @Bindable
    val searchText = MutableLiveData<String>()

    fun search() = viewModelScope.launch(Dispatchers.IO) {

//        var url = "https://www.youtube.com/results?search_query=${searchText.value}&pbj=1";
//
//        val response = url.httpGet()
//            .appendHeader("Host", "www.youtube.com")
//            .appendHeader("Connection", "keep-alive")
//            .appendHeader("X-YouTube-Client-Name", "1")
//            .appendHeader("X-YouTube-Client-Version", "2.20190626")
//            .appendHeader(
//                "Cookie",
//                "VISITOR_INFO1_LIVE=cIdr9kyAxzo; YSC=uiQyEuiTVYU; PREF=f1=50000000; GPS=1; CONSENT=WP.27b70d; ST-13r0l79=oq=nadchodzi%20lato&gs_l=youtube.3..0i71k1l10.0.0.1.431792.0.0.0.0.0.0.0.0..0.0....0...1ac..64.youtube..0.0.0....0.HCy0eUqhZUA&feature=web-masthead-search&itct=CB4Q7VAiEwjau5D-oorjAhWOOZsKHX10AT0o9CQ%3D&csn=bAgVXdrVCI7z7AT96IXoAw"
//            )
//            .appendHeader("Accept", "application/json")
//            .response()
//
//        var temp = response.second.body().asString("application/json")

//        var records = listOf(SongRecord("id1", 1.0, ))

        records.postValue(listOf(SongRecord("rec1", 1.0, "das", "dasd", "dasda"),
            SongRecord("rec2", 1.1, "dasdasda", "dasasd", "da123sda")))
    }
}
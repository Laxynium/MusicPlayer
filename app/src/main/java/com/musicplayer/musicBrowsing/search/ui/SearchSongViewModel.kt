package com.musicplayer.musicBrowsing.search.ui

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicBrowsing.search.domain.SearchSong
import com.musicplayer.musicBrowsing.search.domain.SongDto
import kotlinx.coroutines.launch


class SearchSongViewModel(private val messageBus: MessageBus): ObservableViewModel() {

    @Bindable
    val songs = MutableLiveData<List<SongDto>>()

    @Bindable
    val searchText = MutableLiveData<String>()

    fun search()=viewModelScope.launch{
        val result = messageBus.dispatch(
            SearchSong(
                searchText.value ?: ""
            )
        )
        songs.postValue(result.toList())
    }
}
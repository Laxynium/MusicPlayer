package com.musicplayer.musicBrowsing.search.ui

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.musicplayer.SongDao
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchSongViewModel(private val messageBus: MessageBus, private val dao:SongDao): ObservableViewModel() {

    @Bindable
    val searchText = MutableLiveData<String>()

    fun search() = viewModelScope.launch(Dispatchers.IO) {
        val song = dao.getSong("abc")
        println(song)
    }
}
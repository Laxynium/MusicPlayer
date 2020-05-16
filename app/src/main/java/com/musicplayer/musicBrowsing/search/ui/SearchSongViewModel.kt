package com.musicplayer.musicBrowsing.search.ui

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel

class SearchSongViewModel(private val messageBus: MessageBus): ObservableViewModel() {

    @Bindable
    val searchText = MutableLiveData<String>()

    fun search() {
        println(searchText.value)
    }
}
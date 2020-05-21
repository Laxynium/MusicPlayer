package com.musicplayer.musicBrowsing.search.ui

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchSongViewModel(private val messageBus: MessageBus): ObservableViewModel() {

    @Bindable
    val searchText = MutableLiveData<String>()

    fun search() = viewModelScope.launch(Dispatchers.IO) {
    }
}
package com.musicplayer.musicBrowsing.search.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.musicplayer.framework.MessageBus

class SearchSongViewModel(private val messageBus: MessageBus):ViewModel(){

    val searchText = MutableLiveData<String>()

    fun search() {

    }
}
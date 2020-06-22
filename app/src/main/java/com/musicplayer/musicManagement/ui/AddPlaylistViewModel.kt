package com.musicplayer.musicManagement.ui

import android.view.View
import android.widget.EditText
import androidx.lifecycle.viewModelScope
import com.musicplayer.R
import com.musicplayer.framework.messaging.MessageBus
import com.musicplayer.framework.ui.ObservableViewModel
import com.musicplayer.musicManagement.regularPlaylist.CreateRegularPlaylist
import kotlinx.coroutines.launch
import java.util.*


class AddPlaylistViewModel(private val messageBus: MessageBus) : ObservableViewModel() {
    private lateinit var parentFragment: AddPlaylistFragment

    fun setParent(parent: AddPlaylistFragment) {
        this.parentFragment = parent
    }

    fun addPlaylist(view: View) {
        val name = parentFragment.view?.findViewById(R.id.playlist_name) as EditText
        viewModelScope.launch {
            messageBus.dispatch(CreateRegularPlaylist(UUID.randomUUID(), name.text.toString()))
        }
        parentFragment.moveBack()
    }
}
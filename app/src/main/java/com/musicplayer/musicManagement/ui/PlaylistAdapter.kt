package com.musicplayer.musicManagement.ui

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.musicplayer.framework.ui.DataBoundListAdapter
import com.musicplayer.musicManagement.models.Playlist

class PlaylistAdapter (
    private val viewModel: MusicManagementViewModel
) : DataBoundListAdapter<Playlist>(
    diffCallback = object: DiffUtil.ItemCallback<Playlist>() {
        override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
            return oldItem.songs == newItem.songs && oldItem.name == newItem.name && oldItem.playlistId == newItem.playlistId
        }

    }
) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        TODO("Not yet implemented")
    }

    override fun bind(binding: ViewDataBinding, item: Playlist) {
        TODO("Not yet implemented")
    }
}
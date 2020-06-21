package com.musicplayer.musicManagement.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.musicplayer.R
import com.musicplayer.databinding.PlaylistFromAddToPlaylistBinding
import com.musicplayer.framework.ui.DataBoundListAdapter
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.ui.SongContextViewModel

class PlaylistFromAddToPlaylistAdapter(
    private val viewModel: SongContextViewModel
) : DataBoundListAdapter<Playlist>(
    diffCallback = object : DiffUtil.ItemCallback<Playlist>() {
        override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
            return oldItem.songs == newItem.songs && oldItem.name == newItem.name && oldItem.playlistId == newItem.playlistId
        }

    }
) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.playlist_from_add_to_playlist,
            parent,
            false
        )
    }

    override fun bind(binding: ViewDataBinding, item: Playlist) {
        when (binding) {
            is PlaylistFromAddToPlaylistBinding -> {
                binding.playlist = item
                binding.viewModel = viewModel
            }
        }
    }
}
package com.musicplayer.musicManagement.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.musicplayer.R
import com.musicplayer.databinding.SongBinding
import com.musicplayer.framework.ui.DataBoundListAdapter
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.ui.PlaylistDetailsViewModel

class SongsAdapter (
    private val viewModel: PlaylistDetailsViewModel
) : DataBoundListAdapter<Song>(
    diffCallback = object: DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.song,
            parent,
            false
        )
    }

    override fun bind(binding: ViewDataBinding, item: Song) {
        when(binding){
            is SongBinding -> {
                binding.song = item
                binding.viewModel = viewModel
            }
        }
    }
}
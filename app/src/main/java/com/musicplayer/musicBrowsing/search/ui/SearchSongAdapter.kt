package com.musicplayer.musicBrowsing.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.musicplayer.R
import com.musicplayer.databinding.SearchSongBinding
import com.musicplayer.framework.ui.DataBoundListAdapter
import com.musicplayer.musicBrowsing.search.domain.SongDto

class SearchSongAdapter(
    private val viewModel: SearchSongViewModel
): DataBoundListAdapter<SongDto>(
    diffCallback = object: DiffUtil.ItemCallback<SongDto>() {
        override fun areItemsTheSame(oldItem: SongDto, newItem: SongDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SongDto, newItem: SongDto): Boolean {
            return oldItem == newItem
        }
    }
){
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.search_song,
            parent,
            false
        )
    }

    override fun bind(binding: ViewDataBinding, item: SongDto) {
        when(binding){
            is SearchSongBinding ->{
                binding.searchSong = item
                binding.viewModel = viewModel
            }
        }
    }

}
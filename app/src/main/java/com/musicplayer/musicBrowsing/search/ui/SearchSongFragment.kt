package com.musicplayer.musicBrowsing.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.musicplayer.R
import com.musicplayer.databinding.FragmentSearchSongBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchSongFragment : Fragment() {
    private val viewModel:SearchSongViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.setContentView<FragmentSearchSongBinding>(this.requireActivity(),R.layout.fragment_search_song)
        binding.viewModel = viewModel
        return inflater.inflate(R.layout.fragment_search_song, container, false)
    }
}

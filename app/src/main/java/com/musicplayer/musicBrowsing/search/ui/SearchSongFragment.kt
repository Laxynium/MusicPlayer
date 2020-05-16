package com.musicplayer.musicBrowsing.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.musicplayer.databinding.FragmentSearchSongBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchSongFragment : Fragment() {
    private val viewModel:SearchSongViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchSongBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}

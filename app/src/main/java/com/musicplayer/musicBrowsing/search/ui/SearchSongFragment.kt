package com.musicplayer.musicBrowsing.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.musicplayer.databinding.FragmentSearchSongBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchSongFragment : Fragment() {
    private val viewModel:SearchSongViewModel by viewModel()
    private lateinit var adapter: SearchSongAdapter
    private lateinit var binding: FragmentSearchSongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchSongBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = SearchSongAdapter (
            viewModel
        )

        binding.searchSongs.adapter = adapter

        binding.searchSongs.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

//        adapter.submitList(listOf(SongRecord("rec1", 1.0, "das", "dasd", "dasda"),
//            SongRecord("rec2", 1.1, "dasdasda", "dasasd", "da123sda")))

        viewModel.songs.observeForever {
            adapter.submitList(it)
        }

//        viewModel.onSongsChange {
//            adapter.submitList(it)
//        }
    }
}

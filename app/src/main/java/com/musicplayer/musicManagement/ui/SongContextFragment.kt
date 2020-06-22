package com.musicplayer.musicManagement.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.musicplayer.databinding.FragmentSongContextMenuBinding
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.ui.adapters.PlaylistFromAddToPlaylistAdapter
import com.musicplayer.musicPlaying.ui.MusicPlayingFragment
import org.koin.android.viewmodel.ext.android.viewModel


class SongContextFragment : Fragment() {
    private val viewModel: SongContextViewModel by viewModel()
    private lateinit var binding: FragmentSongContextMenuBinding
    private lateinit var playlist: Playlist
    private lateinit var adapter: PlaylistFromAddToPlaylistAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongContextMenuBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setParent(this)

        adapter = PlaylistFromAddToPlaylistAdapter(
            viewModel
        )

        binding.playlistsRecycleview.adapter = adapter

        binding.playlistsRecycleview.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        viewModel.onPlaylistsChange {
            adapter.submitList(it)
        }

    }

    fun setPlaylist(playlist: Playlist) {
        this.playlist = playlist
    }

    fun setupViewModel(song: Song) {
        viewModel.setSong(song)
    }

    fun moveToPlaying() {
        var detailsFragment = MusicPlayingFragment()
        parentFragmentManager.beginTransaction().replace(id, detailsFragment).commit()
    }

    fun moveBack() {
        var detailsFragment = PlaylistDetailsFragment()
        parentFragmentManager.beginTransaction().replace(id, detailsFragment).commit()
        detailsFragment.setupViewModel(this.playlist)
    }
}

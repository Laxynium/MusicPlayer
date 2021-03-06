package com.musicplayer.musicManagement.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.musicplayer.databinding.FragmentMusicManagementBinding
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.ui.adapters.PlaylistAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class MusicManagementFragment : Fragment() {
    private val viewModel: MusicManagementViewModel by viewModel()
    private lateinit var binding: FragmentMusicManagementBinding
    private lateinit var adapter: PlaylistAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicManagementBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setParent(this)

        adapter = PlaylistAdapter(
            viewModel
        )

        binding.playlistsRecycleview.adapter = adapter

        binding.playlistsRecycleview.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        viewModel.onPlaylistsChange{
            adapter.submitList(it)
        }
    }

    fun moveToDetails(playlist: Playlist) {
        var detailsFragment = PlaylistDetailsFragment()
        parentFragmentManager.beginTransaction().replace(id, detailsFragment).commit()
        detailsFragment.setupViewModel(playlist)
    }

    fun moveToAddPlaylist() {
        parentFragmentManager.beginTransaction().replace(id, AddPlaylistFragment()).commit()
    }
}

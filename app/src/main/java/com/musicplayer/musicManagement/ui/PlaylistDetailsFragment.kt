package com.musicplayer.musicManagement.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.musicplayer.databinding.FragmentPlaylistDetailsBinding
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.models.Song
import com.musicplayer.musicManagement.ui.adapters.SongsAdapter
import com.musicplayer.musicPlaying.ui.MusicPlayingFragment
import org.koin.android.viewmodel.ext.android.viewModel

class PlaylistDetailsFragment : Fragment() {
    private val viewModel: PlaylistDetailsViewModel by viewModel()
    private lateinit var binding: FragmentPlaylistDetailsBinding
    private lateinit var adapter: SongsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaylistDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setParent(this)

        adapter = SongsAdapter(
            viewModel
        )

        binding.songsRecyclerview.adapter = adapter

        binding.songsRecyclerview.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        viewModel.onSongsChanged {
            adapter.submitList(it)
        }
    }

    fun setupViewModel(playlist: Playlist) {
        viewModel.setup(playlist)
    }

    fun moveBack() {
        var fragment = MusicManagementFragment()
        parentFragmentManager.beginTransaction().replace(id, fragment).commit()
    }

    fun moveToPlayingView() {
        var fragment = MusicPlayingFragment()
        parentFragmentManager.beginTransaction().replace(id, fragment).commit()
    }

    fun moveToSongContextMenu(song: Song, playlist: Playlist) {
        var fragment = SongContextFragment()
        parentFragmentManager.beginTransaction().replace(id, fragment).commit()
        fragment.setPlaylist(playlist)
        fragment.setupViewModel(song)
    }
}

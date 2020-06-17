package com.musicplayer.musicManagement.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.musicplayer.databinding.FragmentAddPlaylistBinding
import com.musicplayer.databinding.FragmentMusicManagementBinding
import com.musicplayer.musicManagement.models.Playlist
import com.musicplayer.musicManagement.ui.adapters.PlaylistAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class AddPlaylistFragment : Fragment() {
    private val viewModel: AddPlaylistViewModel by viewModel()
    private lateinit var binding: FragmentAddPlaylistBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPlaylistBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setParent(this)

    }

    fun moveBack() {
        var fragment = MusicManagementFragment()
        parentFragmentManager.beginTransaction().replace(id, fragment).commit()
    }
}

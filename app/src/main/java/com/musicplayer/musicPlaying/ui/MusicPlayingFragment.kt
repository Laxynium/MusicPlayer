package com.musicplayer.musicPlaying.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.musicplayer.databinding.FragmentMusicPlayingBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MusicPlayingFragment : Fragment() {
    private val viewModel: MusicPlayingViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMusicPlayingBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        return binding.root
    }
}

package com.musicplayer.musicManagement.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.musicplayer.databinding.FragmentMusicManagementBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MusicManagementFragment : Fragment() {
    private val viewModel: MusicManagementViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMusicManagementBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}

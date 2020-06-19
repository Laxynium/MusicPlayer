package com.musicplayer.musicPlaying.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.musicplayer.R
import com.musicplayer.databinding.FragmentMusicPlayingBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MusicPlayingFragment : Fragment() {
    private val viewModel: MusicPlayingViewModel by sharedViewModel()
    private lateinit var adapter: QueueSongAdapter
    private lateinit var binding: FragmentMusicPlayingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicPlayingBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = QueueSongAdapter(
            viewModel
        )

        binding.queueSongs.adapter = adapter

        binding.queueSongs.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        viewModel.songsObservable().observeForever {
            adapter.submitList(it)
        }
        val progressBar = view?.findViewById<SeekBar>(R.id.progressBar)
        progressBar?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    viewModel.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}

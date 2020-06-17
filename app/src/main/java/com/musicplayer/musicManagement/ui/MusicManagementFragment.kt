package com.musicplayer.musicManagement.ui

import android.R
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import com.musicplayer.databinding.FragmentMusicManagementBinding
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
//        viewModel. {
////            adapter.submitList(it)
////        }
//        val progressBar = view?.findViewById<SeekBar>(R.id.progressBar)
//        progressBar?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                if(fromUser){
//                    viewModel.seekTo(progress)
//                }
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//            }
//        })
    }

    fun replaceFragment(someFragment: Fragment) {
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.replace(com.musicplayer.R.id.fragment_container, someFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

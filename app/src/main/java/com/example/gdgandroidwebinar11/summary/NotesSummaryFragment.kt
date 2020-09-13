package com.example.gdgandroidwebinar11.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gdgandroidwebinar11.AppViewModelFactory
import com.example.gdgandroidwebinar11.data.NotesService
import com.example.gdgandroidwebinar11.databinding.FragmentNotesSummaryBinding
import com.example.gdgandroidwebinar11.main.MainViewModel

class NotesSummaryFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels {
        AppViewModelFactory(
            NotesService
        )
    }
    private lateinit var binding: FragmentNotesSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesSummaryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.mainViewModel = mainViewModel
        return binding.root
    }
}
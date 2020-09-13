package com.example.gdgandroidwebinar11.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gdgandroidwebinar11.AppViewModelFactory
import com.example.gdgandroidwebinar11.data.NotesService
import com.example.gdgandroidwebinar11.databinding.FragmentNoteAddBinding
import kotlinx.android.synthetic.main.fragment_note_add.*

class NoteAddFragment : Fragment() {

    private val viewModel: NoteAddViewModel by viewModels { AppViewModelFactory(NotesService) }
    private lateinit var binding: FragmentNoteAddBinding

    companion object {
        fun newInstance() = NoteAddFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteAddBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveButton.setOnClickListener {
            viewModel.addNote {
                activity?.onBackPressed()
            }
        }
    }
}
package com.example.gdgandroidwebinar11.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.gdgandroidwebinar11.AppViewModelFactory
import com.example.gdgandroidwebinar11.R
import com.example.gdgandroidwebinar11.data.NotesService
import com.example.gdgandroidwebinar11.databinding.FragmentNotesListBinding
import com.example.gdgandroidwebinar11.main.MainViewModel
import com.example.gdgandroidwebinar11.note.NoteAddFragment
import kotlinx.android.synthetic.main.fragment_notes_list.*
import kotlinx.coroutines.launch

class NotesListFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels {
        AppViewModelFactory(
            NotesService
        )
    }
    private lateinit var binding: FragmentNotesListBinding
    private val notesAdapter: NotesAdapter = NotesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.mainViewModel = mainViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesRecyclerView.apply {
            adapter = notesAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }

        addNoteButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(
                    R.id.container,
                    NoteAddFragment.newInstance(),
                    NoteAddFragment::class.simpleName
                )
                addToBackStack(NoteAddFragment::class.simpleName)
                commit()
            }
        }

        lifecycleScope.launch {
            mainViewModel.notes.observe(viewLifecycleOwner, Observer { notes ->
                notesAdapter.submitList(notes)
            })
        }
    }
}
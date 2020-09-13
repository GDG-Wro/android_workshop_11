package com.example.gdgandroidwebinar11.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.gdgandroidwebinar11.AppViewModelFactory
import com.example.gdgandroidwebinar11.R
import com.example.gdgandroidwebinar11.data.NotesService
import com.example.gdgandroidwebinar11.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_notes_summary.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class NotesSummaryFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels {
        AppViewModelFactory(
            NotesService
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            mainViewModel.notes.collect { notes ->
                val notesCount = notes.count { it.date.month == Date().month }
                notesThisMonth.text = notesCount.toString()
            }
        }
    }
}
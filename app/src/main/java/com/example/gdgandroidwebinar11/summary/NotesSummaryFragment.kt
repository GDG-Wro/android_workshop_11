package com.example.gdgandroidwebinar11.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gdgandroidwebinar11.R
import com.example.gdgandroidwebinar11.data.INotesService
import com.example.gdgandroidwebinar11.data.NotesService
import kotlinx.android.synthetic.main.fragment_notes_summary.*
import kotlinx.coroutines.launch
import java.util.*

class NotesSummaryFragment : Fragment() {

    private val notesService: INotesService = NotesService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val notesCount = notesService.getNotes().count { it.date.month == Date().month }
            notesThisMonth.text = notesCount.toString()
        }
    }
}
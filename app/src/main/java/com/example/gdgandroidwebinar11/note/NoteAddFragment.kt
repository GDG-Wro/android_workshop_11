package com.example.gdgandroidwebinar11.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gdgandroidwebinar11.AppViewModelFactory
import com.example.gdgandroidwebinar11.R
import com.example.gdgandroidwebinar11.data.NotesService
import com.example.gdgandroidwebinar11.models.Note
import kotlinx.android.synthetic.main.fragment_note_add.*
import java.util.*

class NoteAddFragment : Fragment() {

    private val viewModel: NoteAddViewModel by viewModels { AppViewModelFactory(NotesService) }

    companion object {
        fun newInstance() = NoteAddFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveButton.setOnClickListener {
            val noteText = noteEditText.text?.toString()
            val noteDate = datePicker.let { Date(it.year, it.month, it.dayOfMonth) }

            if (!noteText.isNullOrEmpty()) {
                viewModel.addNote(Note(noteText, noteDate)) {
                    activity?.onBackPressed()
                }
            }
        }
    }
}
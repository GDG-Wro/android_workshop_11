package com.example.gdgandroidwebinar11.note

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdgandroidwebinar11.data.INotesService
import com.example.gdgandroidwebinar11.models.Note
import kotlinx.coroutines.launch

class NoteAddViewModel(private val notesService: INotesService) : ViewModel() {

    init {
        Log.w("NoteAddViewModel", "init viewModel")
    }

    fun addNote(note: Note, callback: () -> Unit) {
        viewModelScope.launch {
            notesService.addNote(note)
            callback.invoke()
        }
    }
}
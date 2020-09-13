package com.example.gdgandroidwebinar11.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gdgandroidwebinar11.data.INotesService
import com.example.gdgandroidwebinar11.models.Note
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val notesService: INotesService) : ViewModel() {
    var notes: Flow<List<Note>> = notesService.getNotes()

    init {
        Log.w("MainViewModel", "init viewModel")
    }
}
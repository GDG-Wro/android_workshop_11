package com.example.gdgandroidwebinar11.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.gdgandroidwebinar11.data.INotesService
import com.example.gdgandroidwebinar11.models.Note
import java.util.*

class MainViewModel(private val notesService: INotesService) : ViewModel() {
    val notes: LiveData<List<Note>> = notesService.getNotes().asLiveData()

    val notesCount = Transformations.map(notes) {
        it.size
    }

    val notesInThisMonth = Transformations.map(notes) {
        it.count { it.date.month == Date().month }
    }

    init {
        Log.w("MainViewModel", "init viewModel")
    }
}
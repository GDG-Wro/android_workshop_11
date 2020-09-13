package com.example.gdgandroidwebinar11

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gdgandroidwebinar11.data.INotesService
import com.example.gdgandroidwebinar11.main.MainViewModel
import com.example.gdgandroidwebinar11.note.NoteAddViewModel

class AppViewModelFactory(private val service: INotesService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            NoteAddViewModel::class.java -> NoteAddViewModel(service) as T
            MainViewModel::class.java -> MainViewModel(service) as T
            else -> throw IllegalArgumentException()
        }
    }
}
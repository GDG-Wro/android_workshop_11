package com.example.gdgandroidwebinar11.note

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdgandroidwebinar11.data.INotesService
import com.example.gdgandroidwebinar11.models.Note
import kotlinx.coroutines.launch
import java.util.*

class NoteAddViewModel(private val notesService: INotesService) : ViewModel() {

    val noteText = MutableLiveData<String>()
    val noteDate = MutableLiveData<Date>(Date())

    init {
        Log.w("NoteAddViewModel", "init viewModel")
    }

    fun addNote(callback: () -> Unit) {
        val textValue = noteText.value ?: return
        val dateValue = noteDate.value ?: return

        viewModelScope.launch {
            notesService.addNote(Note(textValue, dateValue))
            callback.invoke()
        }
    }
}
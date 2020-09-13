package com.example.gdgandroidwebinar11.note

import android.util.Log
import androidx.lifecycle.LiveData
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

    private val _savingState = MutableLiveData<SavingState>()
    val savingState: LiveData<SavingState> = _savingState

    init {
        Log.w("NoteAddViewModel", "init viewModel")
    }

    fun addNote(callback: () -> Unit) {
        val textValue = noteText.value ?: return
        val dateValue = noteDate.value ?: return

        viewModelScope.launch {
            _savingState.value = SavingState.Saving
            try {
                notesService.addNote(Note(textValue, dateValue))
                _savingState.postValue(SavingState.Success)
                callback.invoke()
            } catch (e: Exception) {
                _savingState.postValue(SavingState.Error)
            }
        }
    }
}

enum class SavingState {
    Success,
    Saving,
    Error
}
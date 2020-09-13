package com.example.gdgandroidwebinar11.data

import com.example.gdgandroidwebinar11.models.Note
import kotlinx.coroutines.flow.Flow

interface INotesService {
    fun getNotes(): Flow<List<Note>>
    suspend fun addNote(note: Note)
}
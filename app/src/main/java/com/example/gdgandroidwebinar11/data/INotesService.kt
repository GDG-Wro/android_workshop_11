package com.example.gdgandroidwebinar11.data

import com.example.gdgandroidwebinar11.models.Note

interface INotesService {
    suspend fun getNotes(): List<Note>
    suspend fun addNote(note: Note)
}
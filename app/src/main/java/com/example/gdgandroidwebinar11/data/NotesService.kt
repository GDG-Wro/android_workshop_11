package com.example.gdgandroidwebinar11.data

import android.util.Log
import com.example.gdgandroidwebinar11.models.Note
import java.util.*

object NotesService : INotesService {
    private val notes = mutableListOf(
        Note("This is first note", Date()),
        Note("This is second note", Date()),
        Note("This is third note", Date())
    )

    override suspend fun getNotes(): List<Note> {
        Log.w(NotesService::class.simpleName, "getNotes")
        return notes
    }

    override suspend fun addNote(note: Note) {
        notes.add(note)
    }
}
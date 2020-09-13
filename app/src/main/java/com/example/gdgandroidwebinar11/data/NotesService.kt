package com.example.gdgandroidwebinar11.data

import android.util.Log
import com.example.gdgandroidwebinar11.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

object NotesService : INotesService {
    private var notes = listOf(
        Note("This is first note", Date()),
        Note("This is second note", Date()),
        Note("This is third note", Date())
    )

    private val notesFlow = MutableStateFlow(notes)

    override fun getNotes(): Flow<List<Note>> {
        Log.w(NotesService::class.simpleName, "getNotes")
        return notesFlow
    }

    override suspend fun addNote(note: Note) {
        notes = notes.plus(note)
        notesFlow.value = notes
    }
}
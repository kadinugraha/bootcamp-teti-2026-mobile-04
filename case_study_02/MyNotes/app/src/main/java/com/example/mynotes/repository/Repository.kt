package com.example.mynotes.repository

import com.example.mynotes.model.Note
import com.example.mynotes.model.NoteRequest
import com.example.mynotes.remote.RetrofitClient

class NoteRepository {
    private val api = RetrofitClient.api

    suspend fun getNotes() =
        api.getNotes()

    suspend fun addNote(title:String, content:String) =
        api.addNote(
            NoteRequest(title, content)
        )

    suspend fun updateNote(note: Note) =
        api.updateNote(
            note.id,
            NoteRequest(note.title, note.content)
        )

    suspend fun deleteNote(id:Int) =
        api.deleteNote(id)
}


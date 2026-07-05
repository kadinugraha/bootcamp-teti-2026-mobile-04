package com.example.mynotes.model

data class Note(
    val id: Int,
    val title: String,
    val content: String
)

data class NoteRequest(
    val title: String,
    val content: String
)
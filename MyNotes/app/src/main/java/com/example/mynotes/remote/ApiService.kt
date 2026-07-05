package com.example.mynotes.remote

import com.example.mynotes.model.Note
import com.example.mynotes.model.NoteRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("notes")
    suspend fun getNotes(): List<Note>

    @POST("notes")
    suspend fun addNote(
        @Body request: NoteRequest
    ): Note

    @PUT("notes/{id}")
    suspend fun updateNote(
        @Path("id") id:Int,
        @Body request: NoteRequest
    ): Note

    @DELETE("notes/{id}")
    suspend fun deleteNote(
        @Path("id") id:Int
    )
}


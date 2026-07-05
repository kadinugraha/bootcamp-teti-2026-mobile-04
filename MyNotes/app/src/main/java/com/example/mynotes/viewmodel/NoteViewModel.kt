package com.example.mynotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.model.Note
import com.example.mynotes.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {
    private val repository = NoteRepository()
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    fun loadNotes() {
        viewModelScope.launch {
            try {
                _notes.value = repository.getNotes()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addNote(title:String, content:String){
        viewModelScope.launch {
            repository.addNote(title, content)
            loadNotes()
        }
    }

    fun deleteNote(id:Int){
        viewModelScope.launch {
            repository.deleteNote(id)
            loadNotes()
        }

    }

    fun updateNote(note: Note){
        viewModelScope.launch {
            repository.updateNote(note)
            loadNotes()
        }
    }
}
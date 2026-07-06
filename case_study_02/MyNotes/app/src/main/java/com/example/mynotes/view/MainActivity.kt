package com.example.mynotes.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.utilities.NoteAdapter
import com.example.mynotes.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        adapter = NoteAdapter(
            onDelete = { note ->
                viewModel.deleteNote(note.id)
            },
            onEdit = { note ->
                viewModel.updateNote(
                    note.copy(
                        title = note.title + " (Edited)",
                        content = note.content + " (Edited)"
                    )
                )
            }
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.notes.observe(this) { notes ->
            adapter.submitList(notes)
        }

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etContent = findViewById<EditText>(R.id.etContent)
        btnAdd.setOnClickListener {
            viewModel.addNote(
                etTitle.text.toString(),
                etContent.text.toString()
            )
        }

        viewModel.loadNotes()
    }
}
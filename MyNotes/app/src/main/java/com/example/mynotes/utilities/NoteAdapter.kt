package com.example.mynotes.utilities

import com.example.mynotes.R
import com.example.mynotes.model.Note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val onDelete: (Note) -> Unit,
    private val onEdit: (Note) -> Unit
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(DiffCallback()) {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvContent: TextView = itemView.findViewById(R.id.tvContent)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)

        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int
    ) {

        val note = getItem(position)

        holder.tvTitle.text = note.title
        holder.tvContent.text = note.content

        holder.btnEdit.setOnClickListener {
            onEdit(note)
        }

        holder.btnDelete.setOnClickListener {
            onDelete(note)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean {
            return oldItem == newItem
        }
    }
}
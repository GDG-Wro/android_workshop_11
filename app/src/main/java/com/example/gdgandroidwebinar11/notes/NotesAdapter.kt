package com.example.gdgandroidwebinar11.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gdgandroidwebinar11.R
import com.example.gdgandroidwebinar11.models.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter : ListAdapter<Note, NotesAdapter.NoteViewHolder>(NoteDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object NoteDiffUtil : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.text == newItem.text && oldItem.date == newItem.date
        }
    }

    inner class NoteViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(note: Note) {
            with(view) {
                noteText.text = note.text

                val date = note.date
                val dateString = SimpleDateFormat("dd.MM.YYYY", Locale.getDefault()).format(date)
                noteDate.text = dateString
            }
        }
    }
}


package com.example.noteroom.adapter.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteroom.databinding.NoteRecyclerviewItemBinding
import com.example.noteroom.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    // DiffUtil --> to increase the overall performance of the recyclerview
    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), // using viewBinding here
            parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        // TODO: change color of the note
        holder.itemBinding.apply {
            tvNoteTitle.text = currentNote.noteTitle
            tvNoteBody.text = currentNote.noteBody
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class NoteViewHolder(
        val itemBinding: NoteRecyclerviewItemBinding // using viewBinding here --> must be declared val!!
    ) : RecyclerView.ViewHolder(itemBinding.root) {}
}
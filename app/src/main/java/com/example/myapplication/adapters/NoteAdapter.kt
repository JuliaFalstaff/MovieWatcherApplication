package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.HistoryFragmentRecyclerItemBinding
import com.example.myapplication.databinding.NoteFragmentRecyclerItemBinding
import com.example.myapplication.model.data.Movie
import com.example.myapplication.room.NoteEntity
import com.example.myapplication.view.MainFragment
import com.squareup.picasso.Picasso


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var noteData: List<Movie> = listOf()

    fun setData(note: List<Movie>) {
        noteData = note
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteFragmentRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        )
        return NoteViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteData[position])
    }

    override fun getItemCount(): Int {
        return noteData.size
    }


    inner class NoteViewHolder(private val binding: NoteFragmentRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataNote: Movie) {
            binding.apply {
                textViewTitleMovieNote.text = dataNote.title
                editTextNote.setText(dataNote.note)
                Picasso.get().load("https://image.tmdb.org/t/p/w500${dataNote.backdrop_path}").into(imageViewBackgroundPosterNote)
            }
        }
    }
}

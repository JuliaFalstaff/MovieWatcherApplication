package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.HistoryFragmentRecyclerItemBinding
import com.example.myapplication.model.data.Movie
import com.squareup.picasso.Picasso

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var data: List<Movie> = arrayListOf()
    
    fun setData(data: List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.HistoryViewHolder {
        val binding = HistoryFragmentRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
       return data.size
    }

    inner class HistoryViewHolder(private  val binding: HistoryFragmentRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            binding.apply {
                textViewMovieTitle.text = data.title
                textViewRuntimeHistory.text = data.runtime.toString()
                Picasso.get().load("https://image.tmdb.org/t/p/w500${data.poster_path}").into(imageViewPosterHistory)
            }
        }
    }
}
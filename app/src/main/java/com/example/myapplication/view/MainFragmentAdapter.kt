package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.MainFragmentRecyclerItemBinding
import com.example.myapplication.model.data.Movie


class MainFragmentAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>()  {

    private var movieData: List<Movie> = listOf()

    fun removeListener() {
        onItemViewClickListener = null
    }

    fun setMovie(data: List<Movie>) {
        movieData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentAdapter.MainViewHolder {
        val binding = MainFragmentRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainFragmentAdapter.MainViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    inner class MainViewHolder(private val binding: MainFragmentRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = with(binding) {
            textViewPopularity.text = movie.averageVote.toString()
            textViewOriginalTitle.text = movie.originalTitle
            textViewTitle.text = movie.title
            textViewGenre.text = movie.genres
            textViewYearOfRelease.text = movie.releaseDate.toString()
            imageViewPoster.setImageResource(movie.posterPath)
            root.setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
        }
    }
}
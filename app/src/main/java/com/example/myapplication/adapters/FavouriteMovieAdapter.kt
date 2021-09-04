package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FavouriteMovieFragmentRecyclerItemBinding
import com.example.myapplication.model.data.Movie
import com.squareup.picasso.Picasso

class FavouriteMovieAdapter: RecyclerView.Adapter<FavouriteMovieAdapter.FavouriteViewHolder>() {

    private var data: List<Movie> = arrayListOf()
    
    fun setData(data: List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val binding = FavouriteMovieFragmentRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
       return data.size
    }

    inner class FavouriteViewHolder(private  val binding: FavouriteMovieFragmentRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            binding.apply {
                textViewTitle.text = data.title
                textViewYearOfRelease.text = data.release_date.toString()
                textViewPopularity.text = data.vote_average.toString()
                Picasso.get().load("https://image.tmdb.org/t/p/w500${data.poster_path}").into(imageViewPoster)
            }
        }
    }
}
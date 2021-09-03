package com.example.myapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.TopRatedMovieFragmentRecyclerItemBinding
import com.example.myapplication.model.data.Movie
import com.example.myapplication.view.TopRatedMovieFragment
import com.squareup.picasso.Picasso

class TopRatedMovieAdapter (private var onItemViewClickListener: TopRatedMovieFragment.OnItemViewClickListener?) : RecyclerView.Adapter<TopRatedMovieAdapter.TopRatedViewHolder>() {

    private var movieData: MutableList<Movie> = mutableListOf()

    fun setTopRatedMovie (data: MutableList<Movie>) {
        movieData = data
        notifyDataSetChanged()
        Log.i("ANDROID", "set rated movie in adapter")
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopRatedMovieAdapter.TopRatedViewHolder {
        val binding = TopRatedMovieFragmentRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TopRatedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedMovieAdapter.TopRatedViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }


    inner class TopRatedViewHolder(private val binding: TopRatedMovieFragmentRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) = with (binding) {
            textViewTitle.text = movie.title
            textViewPopularity.text = movie.vote_average.toString()
            textViewYearOfRelease.text = movie.release_date.toString()
            Picasso
                .get()
                .load(BASE_URL + movie.poster_path)
                .into(imageViewPoster)
            root.setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
        }
    }

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }
}
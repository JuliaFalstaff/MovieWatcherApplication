package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.MainFragmentRecyclerItemBinding
import com.example.myapplication.model.data.Movie
import com.squareup.picasso.Picasso

private const val FILE_SIZE = "w500"
private const val BASE_URL = "https://image.tmdb.org/t/p/w500/"


class MainFragmentAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var movieData: List<Movie> = listOf()

    fun removeListener() {
        onItemViewClickListener = null
    }

    fun setMovie(data: List<Movie>) {
        movieData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainFragmentAdapter.MainViewHolder {
        val binding = MainFragmentRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainFragmentAdapter.MainViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    inner class MainViewHolder(private val binding: MainFragmentRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = with(binding) {
            textViewPopularity.text = movie.vote_average.toString()
            textViewOriginalTitle.text = movie.original_title
            textViewTitle.text = movie.title
            textViewYearOfRelease.text = movie.release_date.toString()
            imageViewPoster.setImageResource(R.drawable.ic_launcher_background)
            Picasso
                .get()
                .load(BASE_URL + movie.poster_path)
                .into(imageViewPoster)
            root.setOnClickListener { onItemViewClickListener?.onItemViewClick(movie) }
        }
    }
}

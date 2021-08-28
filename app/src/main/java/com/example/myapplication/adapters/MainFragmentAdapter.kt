package com.example.myapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.MainFragmentRecyclerItemBinding
import com.example.myapplication.model.data.Movie
import com.example.myapplication.view.MainFragment
import com.example.myapplication.view.MainFragment.Companion.isAdultMovie
import com.squareup.picasso.Picasso

private const val BASE_URL = "https://image.tmdb.org/t/p/w500/"

class MainFragmentAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var movieData: MutableList<Movie> = mutableListOf()

    fun removeListener() {
        onItemViewClickListener = null
    }

    fun setMovie(data: MutableList<Movie>) {
        movieData = data
        checkIsAdultSettings(movieData)
        notifyDataSetChanged()
        Log.i("ANDROID", "set  movie from adapter")
    }

    fun deleteMovie(position: Int) {
        movieData.removeAt(position)
        notifyDataSetChanged()
    }

    private fun checkIsAdultSettings(movieData: MutableList<Movie>) : MutableList<Movie> {
        if (!isAdultMovie) {
            Log.d("Movie", "check in adapter")
            for(i in 0 until movieData.size){
                if(movieData[i].adult){
                    deleteMovie(i)
                }
            }
        }
        return movieData
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

package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentDetailedMovieBinding
import com.example.myapplication.model.data.Movie

class DetailedMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailedMovieBinding

    companion object {
        const val BUNDLE_EXTRA = "movie"
        fun newInstance(bundle: Bundle): DetailedMovieFragment {
            val fragment = DetailedMovieFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailedMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable<Movie>(BUNDLE_EXTRA)
        if (movie != null) {
            binding.textViewDescription.text = movie.overview
            binding.textViewTitle.text = movie.originalTitle
            binding.textViewGenre.text = movie.genres
            binding.textViewYearOfRelease.text = movie.releaseDate.toString()
            binding.imageViewPoster.setImageResource(movie.posterPath)
            binding.textViewPopularity.text = movie.popularity.toString()
            binding.textViewRuntime.text = movie.runtime.toString()
        }
    }
}
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
        with(binding) {
            arguments?.getParcelable<Movie>(BUNDLE_EXTRA)?.let { movie ->
                textViewDescription.text = movie.overview
                textViewTitle.text = movie.originalTitle
                textViewGenre.text = movie.genres
                textViewYearOfRelease.text = movie.releaseDate.toString()
                imageViewPoster.setImageResource(movie.posterPath)
                textViewPopularity.text = movie.popularity.toString()
                textViewRuntime.text = movie.runtime.toString()
            }
        }
    }
}
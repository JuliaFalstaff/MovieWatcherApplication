package com.example.myapplication.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.myapplication.databinding.FragmentDetailedMovieBinding
import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.dto.MovieDTO

class DetailedMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailedMovieBinding
    private lateinit var movieBundle: Movie

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
            savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailedMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBundle = arguments?.getParcelable<Movie>(BUNDLE_EXTRA) ?: Movie()

        with(binding) {
            detailedMovieView.visibility = View.GONE
            detailedLoadingLayout.visibility = View.VISIBLE
            val loader = MovieLoader(onLoadListener, movieBundle.id)
            loader.loadMovie()
        }

    }

    private val onLoadListener = object : MovieLoader.MovieLoaderListener {
        override fun onLoaded(movieDTO: MovieDTO) {
            displayMovie(movieDTO)
        }

        override fun onFailed(throwable: Throwable) {
            Log.e("MOVIE", "onFailed Execption")
        }

    }

    private fun displayMovie(movieDTO: MovieDTO) {
        with(binding) {
            detailedMovieView.visibility = View.VISIBLE
            detailedLoadingLayout.visibility = View.GONE
            val id = movieBundle.id
            textViewOriginalTitle.text = movieDTO.original_title
            textViewDescription.text = movieDTO.overview
            textViewTitle.text = movieDTO.title
            textViewYearOfRelease.text = movieDTO.release_date.toString()
//            imageViewPoster.setImageResource(movieDTO.results?.poster_path.toString())
            textViewPopularity.text = movieDTO.vote_average.toString()
            textViewRuntime.text = movieDTO.runtime.toString()

        }
    }
}
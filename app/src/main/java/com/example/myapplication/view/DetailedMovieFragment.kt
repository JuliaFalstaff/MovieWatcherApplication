package com.example.myapplication.view

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.myapplication.databinding.FragmentDetailedMovieBinding
import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.dto.MovieDTO
import com.google.gson.Gson
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

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
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailedMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            movieBundle = arguments?.getParcelable<Movie>(BUNDLE_EXTRA) ?: Movie()
            main.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE
            val loader = MovieLoader(onLoadListener)
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
            main.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE
            textViewOriginalTitle.text = movieDTO.results?.originalTitle
            textViewDescription.text = movieDTO.results?.overview
            textViewTitle.text = movieDTO.results?.title
            textViewYearOfRelease.text = movieDTO.results?.releaseDate.toString()
//            imageViewPoster.setImageResource(movieDTO.results?.backdropPath)
            textViewPopularity.text = movieDTO.results?.voteAverage.toString()
        }
    }
}
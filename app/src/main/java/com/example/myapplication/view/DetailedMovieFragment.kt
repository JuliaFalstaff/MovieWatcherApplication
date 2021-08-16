package com.example.myapplication.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailedMovieBinding
import com.example.myapplication.model.AppState
import com.example.myapplication.model.data.Movie
import com.example.myapplication.viewmodel.DetailsMovieViewModel
import com.squareup.picasso.Picasso


private const val API_KEY = "3d4eed70b3bf0c001506c22b79833ff1"
private const val LANGUAGE = "en-US"
private const val MAIN_LINK = "https://api.themoviedb.org/3/movie/"
private const val FILE_SIZE = "w500"
private const val BASE_URL = "https://image.tmdb.org/t/p/"


class DetailedMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailedMovieBinding
    private lateinit var movieBundle: Movie

    private val viewModel: DetailsMovieViewModel by lazy {
        ViewModelProvider(this).get(DetailsMovieViewModel::class.java)
    }

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
        viewModel.detailsLiveData.observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getMovieFromRemoteSource(movieBundle.id)
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                detailedMovieView.visibility = View.VISIBLE
                detailedLoadingLayout.visibility = View.GONE
                setMovie(appState.movieData.first())

            }
            is AppState.Loading -> {
                detailedMovieView.visibility = View.GONE
                detailedLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                detailedMovieView.visibility = View.VISIBLE
                detailedLoadingLayout.visibility = View.GONE
                detailedMovieView.showSnackBar(getString(R.string.error_appstate),
                    getString(R.string.reload_appstate),
                    { viewModel.getMovieFromRemoteSource(movieBundle.id) })
            }
        }
    }

    private fun setMovie(movie: Movie) {
        val id = movieBundle.id
        with(binding) {
            textViewOriginalTitle.text = movie.title.toString()
            textViewDescription.text = movie.overview
            textViewTitle.text = movie.title
            textViewYearOfRelease.text = movie.release_date.toString()
            textViewPopularity.text = movie.vote_average.toString()
            textViewRuntime.text = movie.runtime.toString()
            textViewGenre.text = movie.genres.toString()
        }
        Picasso
            .get()
            .load(BASE_URL + FILE_SIZE + movie.poster_path)
            .into(binding.imageViewPoster)

        Picasso
            .get()
            .load(BASE_URL + FILE_SIZE + movie.backdrop_path)
            .into(binding.imageViewBackgroundPoster)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}





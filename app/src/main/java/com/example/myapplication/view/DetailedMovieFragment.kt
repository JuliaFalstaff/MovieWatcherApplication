package com.example.myapplication.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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
import com.example.myapplication.service.ID_MOVIE

const val DETAILS_INTENT_FILTER = "DETAILS_INTENT_FILTER"
const val DETAILS_INTENT_EMPTY_EXTRA = "DETAILS_INTENT_EMPTY_EXTRA"
const val DETAILS_LOAD_RESULT_EXTRA = "DETAILS_LOAD_RESULT_EXTRA"
const val DETAILS_DATA_EMPTY_EXTRA = "DETAILS_DATA_EMPTY_EXTRA"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "DETAILS_RESPONSE_SUCCESS_EXTRA"
const val DETAILS_ORIGINAL_TITLE = "DETAILS_ORIGINAL_TITLE"
const val DETAILS_TITLE = "DETAILS_TITLE"
const val DETAILS_OVERVIEW = "DETAILS_OVERVIEW"
const val DETAILS_RELEASE_DATE = "DETAILS_RELEASE_DATE"
const val DETAILS_VOTE_AVERAGE = "DETAILS_VOTE_AVERAGE"
const val DETAILS_RUNTIME = "DETAILS_RUNTIME"
const val DETAILS_POSTER_PATH = "DETAILS_POSTER_PATH"
const val DETAILS_BACKDROP_PATH = "DETAILS_BACKDROP_PATH"
const val DETAILS_RESPONSE_EMPTY_EXTRA = "DETAILS_RESPONSE_EMPTY_EXTRA"
const val DETAILS_REQUEST_ERROR_EXTRA = "DETAILS_REQUEST_ERROR_EXTRA"
const val DETAILS_REQUEST_ERROR_MESSAGE_EXTRA = "DETAILS_REQUEST_ERROR_MESSAGE_EXTRA"
const val DETAILS_ID_MOVIE = "DETAILS_ID_MOVIE"
private const val PROCESS_ERROR = "Обработка ошибки"
private const val DETAILS_URL_MALFORMED_EXTRA = "URL MALFORMED"
private const val INVALID_PROPERTY = 0
private const val INVALID_PROPERTY_STRING = "null"





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

    private val loadResultsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
                DETAILS_INTENT_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_DATA_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_RESPONSE_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_REQUEST_ERROR_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_REQUEST_ERROR_MESSAGE_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_URL_MALFORMED_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_RESPONSE_SUCCESS_EXTRA -> renderData(
                        MovieDTO(
                                intent.getIntExtra(ID_MOVIE, INVALID_PROPERTY
                                ),
                                intent.getStringExtra(DETAILS_ORIGINAL_TITLE),
                                intent.getStringExtra(DETAILS_OVERVIEW),
                                intent.getStringExtra(DETAILS_POSTER_PATH),
                                intent.getStringExtra(DETAILS_BACKDROP_PATH),
                                intent.getStringExtra(DETAILS_RELEASE_DATE),
                                intent.getStringExtra(DETAILS_TITLE),
                                intent.getDoubleExtra(DETAILS_VOTE_AVERAGE, 0.0),
                                intent.getIntExtra(DETAILS_RUNTIME, 0)
                        )
                )
                else -> TODO(PROCESS_ERROR)
            }
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

    private fun renderData(movieDTO: MovieDTO) = with(binding) {
        detailedMovieView.visibility = View.VISIBLE
        detailedLoadingLayout.visibility = View.GONE

        val originalTitle = movieDTO.original_title
        val title = movieDTO.title
        val overview = movieDTO.overview
        val releaseDate = movieDTO.release_date
        val runtime = movieDTO.runtime
        val voteAverage = movieDTO.vote_average.toString()
        if (originalTitle == INVALID_PROPERTY_STRING || title == INVALID_PROPERTY_STRING || overview == INVALID_PROPERTY_STRING ||
                releaseDate == INVALID_PROPERTY_STRING || runtime == INVALID_PROPERTY || voteAverage == INVALID_PROPERTY_STRING) {
            TODO("Обработка ошибки")
        } else {
            val id = movieBundle.id
            textViewOriginalTitle.text = movieDTO.original_title
            textViewDescription.text = movieDTO.overview
            textViewTitle.text = movieDTO.title
            textViewYearOfRelease.text = movieDTO.release_date.toString()
            textViewPopularity.text = movieDTO.vote_average.toString()
            textViewRuntime.text = movieDTO.runtime.toString()
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
            textViewPopularity.text = movieDTO.vote_average.toString()
            textViewRuntime.text = movieDTO.runtime.toString()
        }
    }
}



package com.example.myapplication.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.model.AppState
import com.example.myapplication.model.data.Movie
import com.example.myapplication.view.SettingsFragment.Companion.IS_ADULT_SETTING
import com.example.myapplication.viewmodel.MainViewModel

private const val FIRST_PAGE = 1
private var isAdultMovie: Boolean = false

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(movie: Movie) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, DetailedMovieFragment.newInstance(Bundle().apply {
                        putParcelable(DetailedMovieFragment.BUNDLE_EXTRA, movie)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readSettings()
        binding.mainFragmentRecyclerView.adapter = adapter
        viewModel.liveDataToObserve.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getMoviesListFromServer(FIRST_PAGE)

    }


    private fun readSettings() {
        activity?.let {
            isAdultMovie = it.getPreferences(Context.MODE_PRIVATE).getBoolean(IS_ADULT_SETTING, false)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setMovie(checkIsAdultSettings(appState.movieData as MutableList<Movie>))
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.main.showSnackBar(getString(R.string.error_appstate),
                    getString(R.string.reload_appstate),
                    { viewModel.getMoviesListFromServer(1) })
            }
        }
    }

    private fun checkIsAdultSettings(movieData: MutableList<Movie>) : MutableList<Movie> {
        if (!isAdultMovie) {
                for(i in 0 until movieData.size){
                    if(movieData[i].adult){
                        movieData.removeAt(i)
                    }
                }
            }
        return movieData
    }

    override fun onDestroy() {
        adapter.removeListener()
        _binding = null
        super.onDestroy()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movie: Movie)
    }
}
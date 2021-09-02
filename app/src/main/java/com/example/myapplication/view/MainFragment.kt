package com.example.myapplication.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.adapters.MainFragmentAdapter
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.model.AppState
import com.example.myapplication.model.data.Movie
import com.example.myapplication.view.SettingsFragment.Companion.IS_ADULT_SETTING
import com.example.myapplication.viewmodel.MainViewModel


class MainFragment : Fragment() {

    companion object {
        private const val FIRST_PAGE = 1
        fun newInstance() = MainFragment()
        var isAdultMovie: Boolean = false
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
        Log.d("Movie", "on view created")
        binding.mainFragmentRecyclerView.adapter = adapter
        Log.i("ANDROID", "bind Adapter main")
        viewModel.liveDataToObserve.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getMoviesListFromServer(FIRST_PAGE)
        Log.i("ANDROID", "getMovie w/callback main")
    }

    private fun readSettings() {
        activity?.let {
          isAdultMovie = (it.getPreferences(Context.MODE_PRIVATE).getBoolean(IS_ADULT_SETTING, false))
        }
        Log.d("Movie", "readsettings")
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setMovie(appState.movieData)

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

    override fun onDestroy() {
        adapter.removeListener()
        _binding = null
        super.onDestroy()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movie: Movie)
    }
}
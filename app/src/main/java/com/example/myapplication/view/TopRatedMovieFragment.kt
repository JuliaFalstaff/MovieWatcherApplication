package com.example.myapplication.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.adapters.TopRatedMovieAdapter
import com.example.myapplication.databinding.FragmentTopRatedMovieBinding
import com.example.myapplication.model.AppState
import com.example.myapplication.model.data.Movie
import com.example.myapplication.viewmodel.TopRatedMovieViewModel


class TopRatedMovieFragment : Fragment() {
    private lateinit var binding: FragmentTopRatedMovieBinding

    private val viewModel: TopRatedMovieViewModel by lazy {
        ViewModelProvider(this).get(TopRatedMovieViewModel::class.java)
    }

    private val adapter = TopRatedMovieAdapter (object : TopRatedMovieFragment.OnItemViewClickListener {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopRatedMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topRatedFragmentRecyclerView.adapter = adapter
        Log.i("ANDROID", "binding adapter onViewCreated rated")
        viewModel.liveDataToObserve.observe(viewLifecycleOwner, Observer { renderData(it) })

        viewModel.getTopRatedMovieList(FIRST_PAGE)
        Log.i("ANDROID", "getRatedMovie w/callback")
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.topRatedFragmentRecyclerView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                adapter.setTopRatedMovie(appState.movieData)

            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.topRated.showSnackBar(getString(R.string.error_appstate),
                    getString(R.string.reload_appstate),
                    { viewModel.getTopRatedMovieList(FIRST_PAGE) })
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movie: Movie)
    }

    companion object {

        private const val FIRST_PAGE = 1
        fun newInstance() = TopRatedMovieFragment()
    }
}
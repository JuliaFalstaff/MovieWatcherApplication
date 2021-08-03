package com.example.myapplication.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.model.AppState
import com.example.myapplication.model.data.Movie
import com.example.myapplication.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(movie: Movie) {
            val manager = activity?.supportFragmentManager
            if (manager != null) {
                val bundle = Bundle()
                bundle.putParcelable(DetailedMovieFragment.BUNDLE_EXTRA, movie)
                manager.beginTransaction()
                    .add(R.id.container, DetailedMovieFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mainFragmentRecyclerView.adapter = adapter
        val observer = Observer<AppState> {renderData(it)}
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getMovieFromLocalSource()
    }

    private fun renderData(appState: AppState) {
        when(appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setMovie(appState.movieData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.main, R.string.error_appstate, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.reload_appstate) {viewModel.getMovieFromLocalSource()}
                        .show()
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
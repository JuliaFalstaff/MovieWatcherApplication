package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.adapters.FavouriteMovieAdapter
import com.example.myapplication.databinding.FragmentFavouriteMovieBinding
import com.example.myapplication.model.AppState
import com.example.myapplication.viewmodel.FavouriteMovieViewModel
import kotlinx.android.synthetic.main.fragment_favourite_movie.*

class FavouriteMovieFragment : Fragment() {

    companion object {
        fun newInstance() = FavouriteMovieFragment()
    }

    private lateinit var binding: FragmentFavouriteMovieBinding

    private val viewModel: FavouriteMovieViewModel by lazy {
        ViewModelProvider(this).get(FavouriteMovieViewModel::class.java)
    }

    private val adapter: FavouriteMovieAdapter by lazy {
        FavouriteMovieAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favouriteMovieFragmentRecyclerView.adapter = adapter
        viewModel.liveDataToObserve.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getFavouriteMovie()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.favouriteMovieFragmentRecyclerView.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                adapter.setData(appState.movieData)
            }
            is AppState.Loading -> {
                binding.favouriteMovieFragmentRecyclerView.visibility = View.GONE
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.favouriteMovieFragmentRecyclerView.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                binding.favouriteMovieFragmentRecyclerView.showSnackBar(
                        getString(R.string.error),
                        getString(R.string.reload),
                        {
                            viewModel.getFavouriteMovie()
                        })
            }
        }
    }
}
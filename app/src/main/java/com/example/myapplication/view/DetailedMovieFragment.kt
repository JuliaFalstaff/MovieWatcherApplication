package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailedMovieBinding

class DetailedMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailedMovieBinding

    companion object {
        fun newInstance() = DetailedMovieFragment()
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
}
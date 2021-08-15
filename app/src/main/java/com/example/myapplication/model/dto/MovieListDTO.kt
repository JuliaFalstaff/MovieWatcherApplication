package com.example.myapplication.model.dto

import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.data.MovieList

data class MovieListDTO (val page: Int, val list: ArrayList<Movie>)

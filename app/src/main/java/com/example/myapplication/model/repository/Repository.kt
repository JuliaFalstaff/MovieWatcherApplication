package com.example.myapplication.model.repository


import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.data.MovieList
import com.example.myapplication.model.dto.MovieDTO
import com.example.myapplication.model.dto.MovieListDTO

interface Repository {

    fun getMovieFromServer() : Movie
    fun getMovieFromLocalStorage(page: Int?, callback: retrofit2.Callback<MovieListDTO>)
}
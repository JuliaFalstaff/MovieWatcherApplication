package com.example.myapplication.model.repository


import com.example.myapplication.model.data.Movie

interface Repository {

    fun getMovieFromServer() : Movie
    fun getMovieFromLocalStorage() : List<Movie>
}
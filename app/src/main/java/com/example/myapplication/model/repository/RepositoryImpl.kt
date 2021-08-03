package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.data.getMovieList

class RepositoryImpl : Repository {
    override fun getMovieFromServer(): List<Movie> {
        return getMovieList()
    }

    override fun getMovieFromLocalStorage(): List<Movie> {
        return getMovieList()
    }
}
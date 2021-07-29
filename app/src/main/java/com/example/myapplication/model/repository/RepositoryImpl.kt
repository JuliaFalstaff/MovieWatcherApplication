package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie

class RepositoryImpl : Repository {
    override fun getMovieFromServer(): Movie {
        return Movie()
    }

    override fun getMovieFromLocalStorage(): Movie {
        return Movie()
    }
}
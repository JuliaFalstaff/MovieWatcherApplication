package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.data.MovieList
import retrofit2.Callback

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override fun getMovieFromServer() = Movie()

    override fun getMoviesListFromServer(page: Int, callback: Callback<MovieList>) {
        remoteDataSource.getMovieList(page, callback)
    }

    override fun getMovieTopRatedListFromServer(page: Int, callback: Callback<MovieList>) {
        remoteDataSource.getTopRatedMovieList(page, callback)
    }
}



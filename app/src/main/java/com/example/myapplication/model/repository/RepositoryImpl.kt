package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.dto.MovieDTO
import com.example.myapplication.model.dto.MovieListDTO
import retrofit2.Callback


class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override fun getMovieFromServer() = Movie()
    override fun getMovieFromLocalStorage(page: Int?, callback: Callback<MovieListDTO>) {
        remoteDataSource.getMovieList(page, callback)
    }
}



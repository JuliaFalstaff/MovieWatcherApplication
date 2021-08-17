package com.example.myapplication.model.repository

import com.example.filmapp.model.entites.GenresList
import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.data.MovieList
import com.example.myapplication.model.dto.GenresDTO
import retrofit2.Callback


class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override fun getMovieFromServer() = Movie()
    override fun getMoviesListFromServer(page: Int, callback: Callback<MovieList>) {
        remoteDataSource.getMovieList(page, callback)
    }

    override fun getGenresList(callback: Callback<GenresList>) {
        remoteDataSource.getGenresList(callback)
    }
}



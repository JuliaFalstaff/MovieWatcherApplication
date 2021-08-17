package com.example.myapplication.model.repository


import com.example.filmapp.model.entites.GenresList
import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.data.MovieList
import com.example.myapplication.model.dto.GenresDTO
import retrofit2.Callback

interface Repository {
    fun getMovieFromServer() : Movie
    fun getMoviesListFromServer(page: Int, callback: Callback<MovieList>)
    fun getGenresList(callback: Callback<GenresList>)
}
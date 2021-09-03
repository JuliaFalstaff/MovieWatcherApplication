package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie

interface FavouriteMovieRepository {

    fun getAllFavouriteMovie(): MutableList<Movie>
    fun saveFavouriteMovieEntity(movie: Movie)
}
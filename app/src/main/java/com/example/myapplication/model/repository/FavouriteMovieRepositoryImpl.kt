package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie
import com.example.myapplication.room.FavouriteMovieDao
import com.example.myapplication.view.convertFavouriteMovieEntityToMovie
import com.example.myapplication.view.convertMovieToFavouriteMoveEntity


class FavouriteMovieRepositoryImpl(private val localDataSourceFavouriteMovie: FavouriteMovieDao) : FavouriteMovieRepository {

    override fun getAllFavouriteMovie(): MutableList<Movie> {
        return convertFavouriteMovieEntityToMovie(localDataSourceFavouriteMovie.all())
    }

    override fun saveFavouriteMovieEntity(movie: Movie) {
        return localDataSourceFavouriteMovie.insert(convertMovieToFavouriteMoveEntity(movie))
    }

}
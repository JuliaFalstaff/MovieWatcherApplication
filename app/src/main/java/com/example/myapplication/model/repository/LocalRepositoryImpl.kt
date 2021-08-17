package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie
import com.example.myapplication.room.HistoryDao
import com.example.myapplication.view.convertHistoryEntityToMovie
import com.example.myapplication.view.convertMovieToEntity


class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {

    override fun getAllHistory(): List<Movie> {
        return convertHistoryEntityToMovie(localDataSource.all())
    }

    override fun saveEntity(movie: Movie) {
        return localDataSource.insert(convertMovieToEntity(movie))
    }
}
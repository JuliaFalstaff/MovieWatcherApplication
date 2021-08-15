package com.example.myapplication.model.repository

import com.example.myapplication.model.dto.MovieDTO
import retrofit2.Callback

class DetailsMovieRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsMovieRepository {
    override fun getMovieDetailsFromServer(id: Int?, callback: Callback<MovieDTO>) {
        remoteDataSource.getMovieDetails(id, callback)
    }
}
package com.example.myapplication.model.repository

import com.example.myapplication.model.dto.MovieDTO
import com.example.myapplication.model.dto.MovieListDTO
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY = "3d4eed70b3bf0c001506c22b79833ff1"
private const val LANGUAGE = "en-US"
private const val DEFAULT_PAGE = 1
private const val MAIN_URL = "https://api.themoviedb.org/"

class RemoteDataSource {
    private val movieAPI = Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .addConverterFactory(
                    GsonConverterFactory.create(
                            GsonBuilder().setLenient().create()
                    )
            )
            .build()
            .create(MovieAPI::class.java)

    fun getMovieDetails(id: Int?, callback: Callback<MovieDTO>) {
        movieAPI.getMovie(id, API_KEY, LANGUAGE).enqueue(callback)
    }

    fun getMovieList(page: Int?, callback: retrofit2.Callback<MovieListDTO>) {
        movieAPI.getMovieList(API_KEY, LANGUAGE, DEFAULT_PAGE).enqueue(callback)
    }

}
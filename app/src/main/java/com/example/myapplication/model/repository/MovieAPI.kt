package com.example.myapplication.model.repository

import com.example.myapplication.model.dto.MovieDTO
import com.example.myapplication.model.dto.MovieListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("3/movie/{id}")
    fun getMovie (
            @Path("id") id: Int?,
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ) : Call<MovieDTO>

    @GET("3/movie/popular")
    fun getMovieList (
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ) : Call<MovieListDTO>



}


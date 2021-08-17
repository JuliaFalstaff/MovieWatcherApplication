package com.example.myapplication.model.repository

import com.example.filmapp.model.entites.Genre
import com.example.filmapp.model.entites.GenresList
import com.example.myapplication.model.data.MovieList
import com.example.myapplication.model.dto.GenresDTO
import com.example.myapplication.model.dto.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
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
    ) : Call<MovieList>


    @GET("/genre/movie/list")
    fun getGenres(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ) : Call<GenresList>
}


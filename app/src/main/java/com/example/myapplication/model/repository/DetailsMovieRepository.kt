package com.example.myapplication.model.repository

import com.example.myapplication.model.dto.MovieDTO
import retrofit2.Callback

interface DetailsMovieRepository {
    fun getMovieDetailsFromServer (
            id: Int?,
            callback: Callback<MovieDTO>
    )
}
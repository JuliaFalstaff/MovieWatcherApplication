package com.example.myapplication.model.dto

import com.example.myapplication.model.data.CountriesOfProduction
import com.example.myapplication.model.data.Genre

data class MovieDTO(
        val id: Int?,
        val original_title: String?,
        val overview: String?,
        val poster_path: String?,
        val backdrop_path: String?,
        val release_date: String?,
        val title: String?,
        val vote_average: Double?,
        val runtime: Int?,
        val adult: Boolean?,
        val production_countries: MutableList<CountriesOfProduction>,
        val genres: MutableList<Genre>
)

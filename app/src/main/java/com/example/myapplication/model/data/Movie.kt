package com.example.myapplication.model.data

import com.example.myapplication.R

data class Movie(
        val originalTitle: String = "Lost in Translation",
        val genres: String = "Drama",
        val releaseDate: Int = 2004,
        val overview: String = "Билл Мюррей и Скарлетт Йоханссон в фильме Софии Копполы",
        val posterPath: String = R.drawable.poster_lost_in_translation.toString()
)

fun getDefaultMovie() = Movie(
        "Lost in Translation",
        "Drama",
        2004,
        "Билл Мюррей и Скарлетт Йоханссон в фильме Софии Копполы",
        R.drawable.poster_lost_in_translation.toString()
)

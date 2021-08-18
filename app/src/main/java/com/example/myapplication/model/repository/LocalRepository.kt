package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie

interface LocalRepository {

    fun getAllHistory(): MutableList<Movie>
    fun saveEntity(movie: Movie)
}
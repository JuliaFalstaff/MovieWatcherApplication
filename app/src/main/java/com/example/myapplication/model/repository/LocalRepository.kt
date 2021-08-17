package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie

interface LocalRepository {

    fun getAllHistory(): List<Movie>
    fun saveEntity(movie: Movie)
}
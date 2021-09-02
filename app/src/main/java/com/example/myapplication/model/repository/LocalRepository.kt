package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie

interface LocalRepository {

    fun getAllHistory(): MutableList<Movie>
    fun saveEntity(movie: Movie)
    fun getAllNotes(): List<Movie>
    fun saveNoteEntity(id: Long, movieId: Int?, note: String?)
    fun saveNoteMovieEntity(movie: Movie)
    fun getNoteByMovieId(movieId: Int): Int
}



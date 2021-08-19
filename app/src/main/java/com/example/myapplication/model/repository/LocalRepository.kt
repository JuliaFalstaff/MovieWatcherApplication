package com.example.myapplication.model.repository

import androidx.room.*
import com.example.myapplication.model.data.Movie
import com.example.myapplication.room.NoteEntity

interface LocalRepository {

    fun getAllHistory(): MutableList<Movie>
    fun saveEntity(movie: Movie)

    fun getAllNotes(): List<NoteEntity>
    fun saveNoteEntity(id: Long, movieId: Int?, note: String?)
    fun getNoteByMovieId(movieId: Int): Int

}



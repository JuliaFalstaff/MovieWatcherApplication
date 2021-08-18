package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie
import com.example.myapplication.room.NoteEntity

interface LocalRepository {

    fun getAllHistory(): MutableList<Movie>
    fun saveEntity(movie: Movie)

    fun getAllNotes(): MutableList<Movie>
    fun saveNoteEntity(movieId: Int, title: String, backdrop_path: String, note: String)
    fun getNoteByMovieId(movieId: Int) : List<NoteEntity>

}
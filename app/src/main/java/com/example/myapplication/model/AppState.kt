package com.example.myapplication.model

import com.example.myapplication.model.data.Movie
import com.example.myapplication.room.NoteEntity

sealed class AppState {
    data class Success(val movieData: MutableList<Movie>) : AppState()
    data class SuccessMovieNotes(val movie: List<NoteEntity>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

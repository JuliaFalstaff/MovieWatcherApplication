package com.example.myapplication.view

import android.view.View
import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.dto.MovieDTO
import com.example.myapplication.room.HistoryEntity
import com.example.myapplication.room.NoteEntity
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
    text: String,
    actionText: String,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length)
        .setAction(actionText, action)
        .show()
}

fun convertDtoToModel(movieDTO: MovieDTO): MutableList<Movie> {
    return mutableListOf(
        Movie(
            movieDTO.id,
            movieDTO.original_title,
            movieDTO.title,
            movieDTO.release_date,
            movieDTO.overview,
            movieDTO.poster_path.toString(),
            movieDTO.vote_average,
            movieDTO.runtime,
            movieDTO.backdrop_path.toString()
        )
    )
}


fun convertHistoryEntityToMovie(entityList: List<HistoryEntity>): MutableList<Movie> {
    return entityList.map {
        Movie(
            id = it.film_Id?.toInt(),
            title = it.title,
            poster_path = it.poster_path,
            runtime = it.runtime,
            note = it.note)
    }.toMutableList()
}

fun convertMovieToEntity(movie: Movie): HistoryEntity {
    return HistoryEntity(0, movie.id, movie.title, movie.poster_path, movie.runtime, movie.note)
}

fun convertNoteMovieToEntity(movie: Movie): NoteEntity {
    return NoteEntity(0, movie.id, movie.note)
}

fun convertMovieToNoteEntity(id: Long, movieId: Int?, note: String?): NoteEntity =
    NoteEntity(0,movieId, note)

fun convertEntityToMovieNote(entityList: List<NoteEntity>): List<Movie> {
    return entityList.map{
        Movie(id = it.movieId, note = it.note)
    }
}







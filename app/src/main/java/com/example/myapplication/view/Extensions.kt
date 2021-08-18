package com.example.myapplication.view

import android.view.View
import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.data.MovieList
import com.example.myapplication.model.dto.MovieDTO
import com.example.myapplication.model.dto.MovieListDTO
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
            runtime = it.runtime)
    }.toMutableList()
}

fun convertMovieToEntity(movie: Movie): HistoryEntity {
    return HistoryEntity(0, movie.id, movie.title, movie.poster_path, movie.runtime)
}

fun convertMovieToNoteEntity(movieId: Int, title: String, backdrop_path: String, note: String): NoteEntity {
    return NoteEntity(0, movieId,title, backdrop_path, note)
}


fun convertNoteEntityToMovie(entityList: List<NoteEntity>) : MutableList<Movie> {
    return entityList.map {
        Movie(
                id = it.movieId,
                title = it.title,
                backdrop_path = it.backdrop_path.toString(),
                note = it.note
        )
    }.toMutableList()
}





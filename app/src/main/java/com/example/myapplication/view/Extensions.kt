package com.example.myapplication.view

import android.view.View
import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.dto.MovieDTO
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar ( text: String, actionText: String, action: (View) -> Unit, length: Int = Snackbar.LENGTH_INDEFINITE) {
    Snackbar.make(this, text, length)
            .setAction(actionText, action)
            .show()
}

fun convertDtoToModel(movieDTO: MovieDTO) : List<Movie> {
    return listOf(
            Movie(movieDTO?.id,
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
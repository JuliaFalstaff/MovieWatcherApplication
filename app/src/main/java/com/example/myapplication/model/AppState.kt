package com.example.myapplication.model

import com.example.myapplication.model.data.Movie

sealed class AppState {
    data class Success(val movieData: Movie) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

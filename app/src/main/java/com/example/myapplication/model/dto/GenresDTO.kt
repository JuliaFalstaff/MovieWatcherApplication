package com.example.myapplication.model.dto

import com.example.filmapp.model.entites.Genre

data class GenresDTO(
        val id: Int?,
        val name: String?
)

data class GenresListDTO(
        val genres: List<Genre>
)

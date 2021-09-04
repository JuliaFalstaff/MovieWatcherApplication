package com.example.myapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.model.data.Genre

@Entity
data class FavouriteMovieEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val film_Id: Int?,
        val title: String?,
        val poster_path: String?,
        val runtime: Int?,
        val vote_average: Double?,
)

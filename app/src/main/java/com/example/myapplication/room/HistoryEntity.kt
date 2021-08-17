package com.example.myapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val film_Id: Int?,
    val title: String?,
    val poster_path: String?,
    val vote_average: Double?,
    val runtime: Int?,
    )

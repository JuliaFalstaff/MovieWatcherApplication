package com.example.myapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val idNote: Long,
    val movieId: Int?,
    val title: String?,
    val backdrop_path: String?,
    val note: String?
    )

package com.example.filmapp.model.entites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(val id: Int, val name: String) : Parcelable

@Parcelize
data class GenresList(val genres: List<Genre>) : Parcelable

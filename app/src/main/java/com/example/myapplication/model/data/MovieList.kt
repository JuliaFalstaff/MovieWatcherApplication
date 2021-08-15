package com.example.myapplication.model.data

import android.os.Parcelable
import com.example.myapplication.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieList(
        val results: ArrayList<Movie>
) : Parcelable

package com.example.myapplication.model.data

import android.os.Parcelable
import com.example.myapplication.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieList(
        val id : Int?,
        val original_title: String? = "Original title",
        val title: String? = "Title",
        val release_date: String? = "2020",
        val poster_path: String? = "",
        val vote_average: Double? = 0.0,
) : Parcelable

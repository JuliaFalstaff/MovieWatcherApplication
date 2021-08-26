package com.example.myapplication.model.data

import android.os.Parcelable
import com.example.myapplication.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int? = 0,
    val original_title: String? = "",
    val title: String? = "",
    val release_date: String? = "",
    val overview: String? = "",
    val poster_path: String? = "",
    val vote_average: Double? = 0.0,
    val runtime: Int? = 0,
    val backdrop_path: String? = "",
    val adult: Boolean = false,
    var note: String? = "",
    val production_countries: MutableList<CountriesOfProduction>? = null
) : Parcelable





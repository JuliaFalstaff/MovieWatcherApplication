package com.example.myapplication.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountriesOfProduction(
    val name: String
) : Parcelable

@Parcelize
data class CountryList(
    val production_countries: ArrayList<CountriesOfProduction>
) : Parcelable

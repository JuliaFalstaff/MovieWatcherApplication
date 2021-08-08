package com.example.myapplication.model.repository

import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.data.getMovieList


class RepositoryImpl : Repository {
    override fun getMovieFromServer() = Movie()
    override fun getMovieFromLocalStorage() = getMovieList()
}
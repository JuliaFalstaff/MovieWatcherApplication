package com.example.myapplication.model.repository

import com.example.myapplication.model.data.getMovieList

class RepositoryImpl : Repository {
    override fun getMovieFromServer() = getMovieList()
    override fun getMovieFromLocalStorage() = getMovieList()
}
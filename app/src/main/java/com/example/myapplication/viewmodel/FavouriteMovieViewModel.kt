package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.app.App
import com.example.myapplication.model.AppState
import com.example.myapplication.model.repository.FavouriteMovieRepository
import com.example.myapplication.model.repository.FavouriteMovieRepositoryImpl


class FavouriteMovieViewModel(
        val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
        private val favouriteMovieRepository: FavouriteMovieRepository =
                FavouriteMovieRepositoryImpl(App.getFavouriteMovieDao()),
) : ViewModel() {

    fun getFavouriteMovie() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            liveDataToObserve.postValue(AppState.Success(favouriteMovieRepository.getAllFavouriteMovie()))
        }.start()
    }
}
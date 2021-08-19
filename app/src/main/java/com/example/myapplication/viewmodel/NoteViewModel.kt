package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.app.App
import com.example.myapplication.model.AppState
import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.repository.LocalRepository
import com.example.myapplication.model.repository.LocalRepositoryImpl

class NoteViewModel(
        val noteLiveData: MutableLiveData<AppState> = MutableLiveData(),
        private val historyRepository: LocalRepository = LocalRepositoryImpl(App.getHistoryDao(), App.getNoteDao())
): ViewModel() {

    fun getNoteByMovieId(movieId: Int){
        noteLiveData.value = AppState.Loading
        noteLiveData.value = AppState.Success(historyRepository.getNoteByMovieId(movieId) as MutableList<Movie>)
    }

    fun getAllNotes() {
        noteLiveData.value = AppState.Loading
        noteLiveData.value = AppState.SuccessMovieNotes(historyRepository.getAllNotes())
    }
}
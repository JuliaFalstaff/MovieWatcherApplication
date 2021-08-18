package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.app.App
import com.example.myapplication.model.AppState
import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.repository.LocalRepository
import com.example.myapplication.model.repository.LocalRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class NoteViewModel(
        val noteLiveData: MutableLiveData<AppState> = MutableLiveData(),
        private val noteRepository: LocalRepository = LocalRepositoryImpl(App.getHistoryDao(), App.getNoteDao())
): ViewModel() {

    fun getNoteByMovieId(movieId: Int){
        noteLiveData.value = AppState.Loading
        noteLiveData.value = AppState.Success(noteRepository.getNoteByMovieId(movieId) as MutableList<Movie>)
    }

    fun getAllNotes() {
        noteLiveData.value = AppState.Loading
        noteLiveData.value = AppState.Success(noteRepository.getAllNotes())
    }
}
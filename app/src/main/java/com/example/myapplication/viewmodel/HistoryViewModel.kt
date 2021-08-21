package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.app.App.Companion.getHistoryDao
import com.example.myapplication.app.App.Companion.getNoteDao
import com.example.myapplication.model.AppState
import com.example.myapplication.model.repository.LocalRepository
import com.example.myapplication.model.repository.LocalRepositoryImpl

class HistoryViewModel(
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao(), getNoteDao())
) : ViewModel() {


    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        Thread {
            historyLiveData.postValue(AppState.Success(historyRepository.getAllHistory()))
        }.start()
    }
}
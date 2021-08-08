package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.AppState
import com.example.myapplication.model.repository.Repository
import com.example.myapplication.model.repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private  val repository: Repository = RepositoryImpl()): ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val timer: Long = 1000

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getMovieFromLocalSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(timer)
            liveDataToObserve.postValue(AppState.Success(repository.getMovieFromLocalStorage()))
        }.start()
    }

    fun getMovieFromRemoteSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(timer)
            liveDataToObserve.postValue(AppState.Success(listOf(repository.getMovieFromServer())))
        }.start()
    }
}
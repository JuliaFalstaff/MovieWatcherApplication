package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.AppState
import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.dto.MovieListDTO
import com.example.myapplication.model.repository.RemoteDataSource
import com.example.myapplication.model.repository.Repository
import com.example.myapplication.model.repository.RepositoryImpl
import com.example.myapplication.view.convertListDtoToModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.Thread.sleep

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class MainViewModel(): ViewModel() {

    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val timer: Long = 1000
    private val repositoryImpl: RepositoryImpl =
        RepositoryImpl(RemoteDataSource())
    private val movieList: MutableList<Movie> = mutableListOf()

    fun getMovieListFromRemoteSource(page: Int?) {
        liveDataToObserve.value = AppState.Loading
        repositoryImpl.getMovieFromLocalStorage(page, callback)
    }


    private val callback = object : Callback<MovieListDTO> {
        override fun onResponse(call: Call<MovieListDTO>, response: Response<MovieListDTO>) {
            val serverResponse: MovieListDTO? = response.body()
            liveDataToObserve.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MovieListDTO>, t: Throwable) {
            liveDataToObserve.value = AppState.Error (Throwable(t.message ?: REQUEST_ERROR ))
        }

    }

    private fun checkResponse(serverResponse: MovieListDTO) : AppState {
        serverResponse = response.body()
        if (response.isSuccessful && page != null) {
            list.addAll(page.results.toList())
    }


}


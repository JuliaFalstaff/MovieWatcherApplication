package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.AppState
import com.example.myapplication.model.data.Movie
import com.example.myapplication.model.data.MovieList
import com.example.myapplication.model.repository.RemoteDataSource
import com.example.myapplication.model.repository.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"
private const val DEFAULT_PAGE = 1

class MainViewModel() : ViewModel() {

    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repositoryImpl: RepositoryImpl =
        RepositoryImpl(RemoteDataSource())


    fun getMoviesListFromServer(page: Int) {
        liveDataToObserve.value = AppState.Loading
        repositoryImpl.getMoviesListFromServer(page, callback)
    }

    private val callback = object : Callback<MovieList> {
        override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
            val serverResponse: MovieList? = response.body()
            liveDataToObserve.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MovieList>, t: Throwable) {
            liveDataToObserve.value = AppState.Error(Throwable(t.message ?: REQUEST_ERROR))
        }
    }

    private fun checkResponse(serverResponse: MovieList): AppState {
        val fact = serverResponse.results
        return if (fact.isEmpty()) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(fact)
        }
    }
}





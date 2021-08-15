package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.AppState
import com.example.myapplication.model.dto.MovieDTO
import com.example.myapplication.model.repository.DetailsMovieRepository
import com.example.myapplication.model.repository.DetailsMovieRepositoryImpl
import com.example.myapplication.model.repository.RemoteDataSource
import com.example.myapplication.view.convertDtoToModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"


class DetailsMovieViewModel(
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsMovieRepositoryImpl: DetailsMovieRepository =
        DetailsMovieRepositoryImpl(RemoteDataSource())
) : ViewModel() {

    fun getMovieFromRemoteSource(id: Int?) {
        detailsLiveData.value = AppState.Loading
        detailsMovieRepositoryImpl.getMovieDetailsFromServer(id, callBack)
    }

    private val callBack = object :
        Callback<MovieDTO> {

        @Throws(IOException::class)
        override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
            val serverResponse: MovieDTO? = response.body()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    private fun checkResponse(serverResponse: MovieDTO): AppState {
        return if (serverResponse.id == null || serverResponse.original_title == null ||
            serverResponse.overview == null || serverResponse.poster_path == null || serverResponse.backdrop_path == null ||
            serverResponse.release_date == null || serverResponse.title == null || serverResponse.vote_average == null ||
            serverResponse.runtime == null
        ) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(convertDtoToModel(serverResponse))
        }
    }
}






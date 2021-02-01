package com.example.mymovielibrary.ui.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.model.MovieProperties
import com.example.mymovielibrary.service.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageViewModel : ViewModel() {
    private var _serverResponse = MutableLiveData<String>()
    val serverResponse: LiveData<String> = _serverResponse

    private val _movieProperties = MutableLiveData<List<Movie>>()
    val movieProperties: LiveData<List<Movie>> = _movieProperties

    init {
        suspendMethodCaller()
    }

    private fun suspendMethodCaller() = viewModelScope.launch {
        getAllMovies()
    }

    private suspend fun getAllMovies() = withContext(Dispatchers.IO) {
        MovieApi.retrofitService.getMovies(API_KEY)
                .enqueue(object : Callback<MovieProperties> {
                    override fun onFailure(call: Call<MovieProperties>, t: Throwable) {
                        _serverResponse.postValue("Failure: " + t.message)
                    }

                    override fun onResponse(
                            call: Call<MovieProperties>,
                            response: Response<MovieProperties>
                    ) {
                        if (response != null)
                            _movieProperties.postValue(response.body()?.results)
                    }
                })
    }

    companion object {
        const val API_KEY = "65db5aebb7dc29d77c7b00443904e829"
    }
}

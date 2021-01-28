package com.example.mymovielibrary.ui.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovielibrary.model.MovieProperties
import com.example.mymovielibrary.model.Result
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

    private val _property = MutableLiveData<List<Result>>()
    val property: LiveData<List<Result>> = _property

    init {
        suspendMethodCaller()
    }

    private fun suspendMethodCaller() = viewModelScope.launch {
        getAllMovies()
    }

    private suspend fun getAllMovies() = withContext(Dispatchers.IO) {
        MovieApi.retrofitService.getProperties("65db5aebb7dc29d77c7b00443904e829")
            .enqueue(object : Callback<MovieProperties> {
                override fun onFailure(call: Call<MovieProperties>, t: Throwable) {
                    _serverResponse.postValue("Failure: " + t.message)
                }

                override fun onResponse(
                    call: Call<MovieProperties>,
                    response: Response<MovieProperties>
                ) {
                    val response = response.body()
                    if (response != null)
                        _property.postValue(response.results)
                }
            })
    }
}

package com.example.mymovielibrary.ui.descriptionPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovielibrary.model.Review
import com.example.mymovielibrary.model.ReviewProperties
import com.example.mymovielibrary.model.Trailer
import com.example.mymovielibrary.model.TrailerProperties
import com.example.mymovielibrary.service.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPageViewModel : ViewModel() {

    private var _serverResponse = MutableLiveData<String>()
    val serverResponse: LiveData<String> = _serverResponse

    private val _reviewProperties = MutableLiveData<List<Review>>()
    val reviewProperties: LiveData<List<Review>> = _reviewProperties

    private val _trailerProperties = MutableLiveData<List<Trailer>>()
    val trailerProperties: LiveData<List<Trailer>> = _trailerProperties

    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> = _movieId


    init {
        _movieId.postValue(0)
        suspendMethodCaller()
        _movieId.postValue(0)
    }

    private fun suspendMethodCaller() = viewModelScope.launch {
        getAllReviews()
        //getAllTrailers()
    }

    private suspend fun getAllReviews() = withContext(Dispatchers.IO) {
        MovieApi.retrofitService.getReviews("$movieId", "65db5aebb7dc29d77c7b00443904e829")
                .enqueue(object : Callback<ReviewProperties> {
                    override fun onFailure(call: Call<ReviewProperties>, t: Throwable) {
                        _serverResponse.postValue("Failure: " + t.message)
                    }

                    override fun onResponse(call: Call<ReviewProperties>, response: Response<ReviewProperties>) {
                        val response = response.body()
                        if (response != null)
                            _reviewProperties.postValue(response.reviews)
                    }
                })
    }

    private suspend fun getAllTrailers() = withContext(Dispatchers.IO) {
        MovieApi.retrofitService.getTrailers("$movieId", "65db5aebb7dc29d77c7b00443904e829")
                .enqueue(object : Callback<TrailerProperties> {
                    override fun onFailure(call: Call<TrailerProperties>, t: Throwable) {
                        _serverResponse.postValue("Failure: " + t.message)
                    }

                    override fun onResponse(call: Call<TrailerProperties>, response: Response<TrailerProperties>) {
                        val response = response.body()
                        if (response != null)
                            _trailerProperties.postValue(response.results)
                    }
                })
    }

    fun getMovieId(movieId: Int) {
        _movieId.postValue(movieId)
    }
}

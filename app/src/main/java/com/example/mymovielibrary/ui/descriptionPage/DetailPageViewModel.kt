package com.example.mymovielibrary.ui.descriptionPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovielibrary.data.repository.MovieRepository
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.model.Review
import com.example.mymovielibrary.model.ReviewProperties
import com.example.mymovielibrary.model.Trailer
import com.example.mymovielibrary.model.TrailerProperties
import com.example.mymovielibrary.service.MovieApi
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPageViewModel @Inject constructor(val repository: MovieRepository) : ViewModel() {

    private var _serverResponse = MutableLiveData<String>()
    val serverResponse: LiveData<String> = _serverResponse

    private val _reviewProperties = MutableLiveData<List<Review>>()
    val reviewProperties: LiveData<List<Review>> = _reviewProperties

    private val _trailerProperties = MutableLiveData<List<Trailer>>()
    val trailerProperties: LiveData<List<Trailer>> = _trailerProperties

    private val _isMovieExist = MutableLiveData<Boolean>()
    val isMovieExist: LiveData<Boolean> = _isMovieExist

    fun fetchMovieId(id: Int) = viewModelScope.launch {
        getAllReviews(id)
        getAllTrailers(id)
    }

    private suspend fun getAllReviews(id: Int) = withContext(Dispatchers.IO) {
        MovieApi.retrofitService.getReviews("$id", API_KEY)
            .enqueue(object : Callback<ReviewProperties> {
                override fun onFailure(call: Call<ReviewProperties>, t: Throwable) {
                    _serverResponse.postValue("Failure: " + t.message)
                }

                override fun onResponse(
                    call: Call<ReviewProperties>,
                    response: Response<ReviewProperties>
                ) {
                    _reviewProperties.postValue(response.body()?.reviews)
                }
            })
    }

    private suspend fun getAllTrailers(id: Int) = withContext(Dispatchers.IO) {
        MovieApi.retrofitService.getTrailers("$id", API_KEY)
            .enqueue(object : Callback<TrailerProperties> {
                override fun onFailure(call: Call<TrailerProperties>, t: Throwable) {
                    _serverResponse.postValue("Failure: " + t.message)
                }

                override fun onResponse(
                    call: Call<TrailerProperties>,
                    response: Response<TrailerProperties>
                ) {
                    _trailerProperties.postValue(response.body()?.trailers)
                }
            })
    }

    fun movieFetch(movie: Movie) = viewModelScope.launch {
        if (repository.checkMovieExist(movie.id) == null)
            _isMovieExist.postValue(false)
        else
            _isMovieExist.postValue(true)
    }

    fun handledInsertAndDelete(movie: Movie) = viewModelScope.launch {
        if (isMovieExist.value == false) {
            repository.insertMovie(movie)
            _isMovieExist.postValue(true)
        } else {
            repository.deleteMovie(movie)
            _isMovieExist.postValue(false)
        }
    }

    companion object {
        const val API_KEY = "65db5aebb7dc29d77c7b00443904e829"
    }
}

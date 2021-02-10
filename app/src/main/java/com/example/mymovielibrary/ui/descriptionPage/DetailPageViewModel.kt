package com.example.mymovielibrary.ui.descriptionPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovielibrary.data.repository.MovieRepository
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.model.Review
import com.example.mymovielibrary.model.Trailer
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        val response = repository.fetchAllReviews("$id", API_KEY)
        if (response.isSuccessful) {
            response.body()?.let { reviewProperties ->
                _reviewProperties.postValue(reviewProperties.reviews)
            }
        }
    }

    private suspend fun getAllTrailers(id: Int) = withContext(Dispatchers.IO) {
        val response = repository.fetchAllTrailers("$id", API_KEY)
        if (response.isSuccessful) {
            response.body()?.let { trailerProperties ->
                _trailerProperties.postValue(trailerProperties.trailers)
            }
        }
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

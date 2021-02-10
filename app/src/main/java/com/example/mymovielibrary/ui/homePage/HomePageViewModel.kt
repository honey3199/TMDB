package com.example.mymovielibrary.ui.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovielibrary.data.repository.MovieRepository
import com.example.mymovielibrary.model.Movie
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePageViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    private var _serverResponse = MutableLiveData<String>()
    val serverResponse: LiveData<String> = _serverResponse

    private val _movieProperties = MutableLiveData<List<Movie>>()
    val movieProperties: LiveData<List<Movie>> = _movieProperties

    fun fetchMovies() = viewModelScope.launch {
        getAllMovies()
    }

    private suspend fun getAllMovies() = withContext(Dispatchers.IO) {
        val response = repository.fetchAllMovie(API_KEY)
        if (response.isSuccessful) {
            response.body()?.let {
                _movieProperties.postValue(response.body()?.movies)
            }
        }
    }

    companion object {
        const val API_KEY = "65db5aebb7dc29d77c7b00443904e829"
    }
}

package com.example.mymovielibrary.data.repository

import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.model.MovieProperties
import retrofit2.Response

interface MovieRepository {
    suspend fun insertMovie(movie: Movie)
    suspend fun checkMovieExist(movieId: Int): Movie?
    suspend fun deleteMovie(movie: Movie)

    // remote service
    suspend fun fetchAllMovie(key :String): Response<MovieProperties>
}

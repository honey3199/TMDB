package com.example.mymovielibrary.data.repository

import com.example.mymovielibrary.model.Movie

interface MovieRepository {
    suspend fun insertMovie(movie: Movie)
    suspend fun checkMovieExist(movieId: Int): Movie?
    suspend fun deleteMovie(movie: Movie)
}

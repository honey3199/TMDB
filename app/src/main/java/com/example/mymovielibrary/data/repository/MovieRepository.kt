package com.example.mymovielibrary.data.repository

import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.model.MovieProperties
import com.example.mymovielibrary.model.ReviewProperties
import com.example.mymovielibrary.model.TrailerProperties
import retrofit2.Response

interface MovieRepository {
    suspend fun insertMovie(movie: Movie)
    suspend fun checkMovieExist(movieId: Int): Movie?
    suspend fun deleteMovie(movie: Movie)

    // remote service
    suspend fun fetchAllMovie(key :String): Response<MovieProperties>
    suspend fun fetchAllReviews(id:String,key: String):Response<ReviewProperties>
    suspend fun fetchAllTrailers(id:String,key: String):Response<TrailerProperties>
}

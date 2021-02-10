package com.example.mymovielibrary.service

import com.example.mymovielibrary.model.MovieProperties
import com.example.mymovielibrary.model.ReviewProperties
import com.example.mymovielibrary.model.TrailerProperties
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("popular")
    suspend fun getAllMovies(@Query("api_key") key: String): Response<MovieProperties>

    @GET("{id}/reviews")
    suspend fun getAllReviews(
        @Path("id") id: String,
        @Query("api_key") api_key: String
    ): Response<ReviewProperties>

    @GET("{id}/videos")
    suspend fun getAllTrailers(
        @Path("id") id: String,
        @Query("api_key") api_key: String
    ): Response<TrailerProperties>
}

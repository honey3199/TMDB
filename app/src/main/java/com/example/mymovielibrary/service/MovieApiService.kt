package com.example.mymovielibrary.service

import com.example.mymovielibrary.model.MovieProperties
import com.example.mymovielibrary.model.ReviewProperties
import com.example.mymovielibrary.model.TrailerProperties
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

private val moshiBuilderObject = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshiBuilderObject))
        .baseUrl(BASE_URL)
        .build()

interface MovieApiService {
    @GET("popular")
    fun getMovies(@Query("api_key") key: String): Call<MovieProperties>

    @GET("{id}/reviews")
    fun getReviews(@Path("id") id: String, @Query("api_key") api_key: String): Call<ReviewProperties>

    @GET("{id}/videos")
    fun getTrailers(@Path("id") id: String, @Query("api_key") api_key: String): Call<TrailerProperties>

}

object MovieApi {
    val retrofitService: MovieApiService by lazy { retrofit.create(MovieApiService::class.java) }
}

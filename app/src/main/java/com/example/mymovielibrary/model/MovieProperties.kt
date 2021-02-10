package com.example.mymovielibrary.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class MovieProperties(@SerializedName(  "results")val movies: List<Movie>)

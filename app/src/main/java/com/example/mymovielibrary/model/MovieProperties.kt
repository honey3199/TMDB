package com.example.mymovielibrary.model

import com.google.gson.annotations.SerializedName

data class MovieProperties(@SerializedName("results") val movies: List<Movie>)

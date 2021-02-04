package com.example.mymovielibrary.model

import com.squareup.moshi.Json

data class MovieProperties(@Json(name = "results")val movies: List<Movie>)

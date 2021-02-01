package com.example.mymovielibrary.model

import com.squareup.moshi.Json

data class ReviewProperties(
        val id: Int,
        @Json(name = "results")
        val reviews: List<Review>,
)

package com.example.mymovielibrary.model

import com.squareup.moshi.Json

data class ReviewProperties(
        val id: Int,
        val page: Int,
        @Json(name = "results")
        val reviews: List<Review>,
        val total_pages: Int,
        val total_results: Int
)

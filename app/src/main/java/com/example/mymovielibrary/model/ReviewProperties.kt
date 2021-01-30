package com.example.mymovielibrary.model

data class ReviewProperties(
        val id: Int,
        val page: Int,
        val reviews: List<Review>,
        val total_pages: Int,
        val total_results: Int
)

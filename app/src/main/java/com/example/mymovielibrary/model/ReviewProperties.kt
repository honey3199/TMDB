package com.example.mymovielibrary.model

import com.google.gson.annotations.SerializedName

data class ReviewProperties(
        val id: Int,
        @SerializedName("results")
        val reviews: List<Review>,
)

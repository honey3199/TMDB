package com.example.mymovielibrary.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ReviewProperties(
        val id: Int,
        @SerializedName("results")
        val reviews: List<Review>,
)

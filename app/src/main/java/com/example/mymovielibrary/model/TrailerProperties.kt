package com.example.mymovielibrary.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class TrailerProperties(
        val id: Int,
        @SerializedName("results")
        val trailers: List<Trailer>
)

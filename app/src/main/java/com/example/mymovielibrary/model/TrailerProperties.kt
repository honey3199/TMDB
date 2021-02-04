package com.example.mymovielibrary.model

import com.squareup.moshi.Json

data class TrailerProperties(
        val id: Int,
        @Json(name = "results")
        val trailers: List<Trailer>
)

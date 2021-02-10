package com.example.mymovielibrary.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mymovielibrary.databinding.TrailerBinding
import com.example.mymovielibrary.model.Trailer

class MovieTrailerViewHolder(
    trailerBinding: TrailerBinding
) : RecyclerView.ViewHolder(trailerBinding.root) {
    private val trailerImageContainer = trailerBinding.videoViewForTrailer
    fun setTrailerDataToList(trailer: Trailer) {
        Glide.with(itemView)
            .load("https://img.youtube.com/vi/" + trailer.key + "/0.jpg")
            .apply(
                RequestOptions()
                    .override(500, 500)
            )
            .into(trailerImageContainer)
    }
}

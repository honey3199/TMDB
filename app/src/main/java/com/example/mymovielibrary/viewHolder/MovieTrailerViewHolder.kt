package com.example.mymovielibrary.viewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mymovielibrary.R
import com.example.mymovielibrary.model.Trailer

class MovieTrailerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val trailerImageContainer = view.findViewById<ImageView>(R.id.video_view_for_trailer)
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

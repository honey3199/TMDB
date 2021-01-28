package com.example.mymovielibrary

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL

class MovieLibraryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageMovie: ImageView = view.findViewById(R.id.movie_picture)
    fun setDataToList(movie: Result) {
        Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w185" + movie.poster_path)
                .apply(RequestOptions()
                        .override(SIZE_ORIGINAL, 500))
                .into(imageMovie)
    }
}

package com.example.mymovielibrary.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.mymovielibrary.R
import com.example.mymovielibrary.model.Result

class MovieLibraryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageMovie: ImageView = view.findViewById(R.id.movie_picture)
    val nameMovie: TextView = view.findViewById(R.id.movie_name)
    fun setDataToList(movie: Result) {
        nameMovie.text = movie.title
        Glide.with(itemView)
            .load("https://image.tmdb.org/t/p/w185" + movie.poster_path)
            .apply(
                RequestOptions()
                    .override(SIZE_ORIGINAL, 500)
            )
            .into(imageMovie)
    }
}

package com.example.mymovielibrary.viewHolder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.mymovielibrary.databinding.MovieBinding
import com.example.mymovielibrary.model.Movie

class MovieLibraryViewHolder(
    movieBinding: MovieBinding
) : RecyclerView.ViewHolder(movieBinding.root) {
    private val imageMovie: ImageView = movieBinding.moviePicture
    fun setMovieDataToList(movie: Movie) {
        Glide.with(itemView)
            .load("https://image.tmdb.org/t/p/w185" + movie.poster_path)
            .apply(
                RequestOptions()
                    .override(SIZE_ORIGINAL, 800)
            )
            .into(imageMovie)
    }
}

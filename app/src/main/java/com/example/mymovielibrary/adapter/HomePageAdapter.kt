package com.example.mymovielibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.R
import com.example.mymovielibrary.clickListenerInterface.MovieClickListener
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.viewHolder.MovieLibraryViewHolder

class HomePageAdapter(private val movieClickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieLibraryViewHolder>() {
    private var movieList = mutableListOf<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieLibraryViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)
        return MovieLibraryViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MovieLibraryViewHolder, position: Int) {
        holder.setMovieDataToList(movieList[position])
        holder.itemView.setOnClickListener {
            movieClickListener.onMovieClickListener(movieList[position])
        }
    }

    override fun getItemCount() = movieList.size

    fun setMovieData(movie: List<Movie>) {
        movieList.addAll(movie)
        notifyDataSetChanged()
    }
}

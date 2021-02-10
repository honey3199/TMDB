package com.example.mymovielibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.clickListenerInterface.TrailerClickListener
import com.example.mymovielibrary.databinding.TrailerBinding
import com.example.mymovielibrary.model.Trailer
import com.example.mymovielibrary.viewHolder.MovieTrailerViewHolder

class MovieTrailerAdapter(private val trailerClickListener: TrailerClickListener) :
    RecyclerView.Adapter<MovieTrailerViewHolder>() {
    private var movieTrailerList = mutableListOf<Trailer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieTrailerViewHolder(
        TrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MovieTrailerViewHolder, position: Int) {
        holder.setTrailerDataToList(movieTrailerList[position])
        holder.itemView.setOnClickListener {
            trailerClickListener.onTrailerClickListener(movieTrailerList[position].key)
        }
    }

    override fun getItemCount(): Int = movieTrailerList.size

    fun setTrailerData(trailer: List<Trailer>) {
        movieTrailerList.addAll(trailer)
        notifyDataSetChanged()
    }
}

package com.example.mymovielibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MovieLibraryAdapter : RecyclerView.Adapter<MovieLibraryViewHolder>() {
    private var myMovieList = mutableListOf<Result>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieLibraryViewHolder {
        val adapterLayout =
                LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)
        adapterLayout.setOnClickListener {
            Toast.makeText(parent.context, "Hello", Toast.LENGTH_SHORT).show()
        }
        return MovieLibraryViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MovieLibraryViewHolder, position: Int) {
        holder.setDataToList(myMovieList[position])
    }

    override fun getItemCount() = myMovieList.size
    fun setData(movie: List<Result>) {
        myMovieList.addAll(movie)
        notifyDataSetChanged()
    }
}

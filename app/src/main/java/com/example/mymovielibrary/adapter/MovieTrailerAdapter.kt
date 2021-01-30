package com.example.mymovielibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.R
import com.example.mymovielibrary.model.Trailer
import com.example.mymovielibrary.viewHolder.MovieTrailerViewHolder

class MovieTrailerAdapter : RecyclerView.Adapter<MovieTrailerViewHolder>() {
    private var myTrailerList = mutableListOf<Trailer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTrailerViewHolder {
        val adapterLayout =
                LayoutInflater.from(parent.context).inflate(R.layout.trailer, parent, false)
        return MovieTrailerViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MovieTrailerViewHolder, position: Int) {
        holder.setDataToList(myTrailerList[position])
    }

    override fun getItemCount(): Int = myTrailerList.size

    fun setTrailers(trailer: List<Trailer>) {
        myTrailerList.addAll(trailer)
        notifyDataSetChanged()
    }
}

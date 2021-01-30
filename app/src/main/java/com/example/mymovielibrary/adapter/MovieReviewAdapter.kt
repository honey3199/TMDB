package com.example.mymovielibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.R
import com.example.mymovielibrary.model.Review
import com.example.mymovielibrary.viewHolder.MovieReviewViewHolder

class MovieReviewAdapter : RecyclerView.Adapter<MovieReviewViewHolder>() {
    private var movieReviewList = mutableListOf<Review>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        val adapterLayout =
                LayoutInflater.from(parent.context).inflate(R.layout.review, parent, false)
        return MovieReviewViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        holder.setDataToList(movieReviewList[position])
    }

    override fun getItemCount(): Int = movieReviewList.size

    fun setReviews(review: List<Review>) {
        movieReviewList.addAll(review)
        notifyDataSetChanged()
    }
}

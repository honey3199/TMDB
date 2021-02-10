package com.example.mymovielibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.databinding.ReviewBinding
import com.example.mymovielibrary.model.Review
import com.example.mymovielibrary.viewHolder.MovieReviewViewHolder

class MovieReviewAdapter : RecyclerView.Adapter<MovieReviewViewHolder>() {
    private var movieReviewList = mutableListOf<Review>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieReviewViewHolder(
        ReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        holder.setReviewDataToList(movieReviewList[position])
    }

    override fun getItemCount(): Int = movieReviewList.size

    fun setReviewData(review: List<Review>) {
        movieReviewList.addAll(review)
        notifyDataSetChanged()
    }
}

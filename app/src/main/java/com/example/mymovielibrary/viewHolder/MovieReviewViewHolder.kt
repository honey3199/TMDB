package com.example.mymovielibrary.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.databinding.ReviewBinding
import com.example.mymovielibrary.model.Review

class MovieReviewViewHolder(
    reviewBinding: ReviewBinding
) : RecyclerView.ViewHolder(reviewBinding.root) {
    private val textViewReview = reviewBinding.textViewForReviews
    private val textViewAuthor = reviewBinding.reviewAuthorText
    fun setReviewDataToList(review: Review) {
        textViewReview.text = review.content
        textViewAuthor.text = review.author
    }
}

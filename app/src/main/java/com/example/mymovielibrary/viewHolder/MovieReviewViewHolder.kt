package com.example.mymovielibrary.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.R
import com.example.mymovielibrary.model.Review

class MovieReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textViewReview = view.findViewById<TextView>(R.id.text_view_for_reviews)
    private val textViewAuthor = view.findViewById<TextView>(R.id.review_author_text)
    fun setReviewDataToList(review: Review) {
        textViewReview.text = review.content
        textViewAuthor.text = review.author
    }
}

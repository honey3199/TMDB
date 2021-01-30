package com.example.mymovielibrary.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.R
import com.example.mymovielibrary.model.Trailer

class MovieTrailerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val trailerVideoContainer = view.findViewById<TextView>(R.id.video_view_for_trailer)
    fun setDataToList(trailer: Trailer) {
//        trailerVideoContainer.text = trailer.iso_3166_1
    }
}

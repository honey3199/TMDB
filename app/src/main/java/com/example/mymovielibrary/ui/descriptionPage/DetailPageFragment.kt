package com.example.mymovielibrary.ui.descriptionPage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.mymovielibrary.R
import com.example.mymovielibrary.model.Result

class DetailPageFragment() : Fragment() {

    private lateinit var movie: Result
    private lateinit var viewModel: DetailPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.detail_page_fragment, container, false)
        viewModel = ViewModelProvider(this).get(DetailPageViewModel::class.java)
        movie = arguments?.get(MOVIES) as Result

        val movieNameInImage = view.findViewById<TextView>(R.id.movie_name_detail_page)
        val moviePicture = view.findViewById<ImageView>(R.id.movie_picture_detail_page)
        val movieTitle = view.findViewById<TextView>(R.id.movie_title)
        val movieReleasingYear = view.findViewById<TextView>(R.id.movie_year)
        val movieRating = view.findViewById<RatingBar>(R.id.movie_rating)
        val movieSynopsis = view.findViewById<TextView>(R.id.text_synopsis)

        movieNameInImage.text = movie.title
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w185" + movie.poster_path)
            .apply(
                RequestOptions()
                    .override(Target.SIZE_ORIGINAL, 500)
            )
            .into(moviePicture)

        movieTitle.text = movie.title
        movieReleasingYear.text = movie.release_date
        movieRating.rating = movie.vote_average.toFloat() / 2
        movieSynopsis.text = movie.overview

//        view.findViewById<RecyclerView>(R.id.recycler_view).setHasFixedSize(true)


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object{
        const val MOVIES = "MOVIES"
        fun build(result: Result)= DetailPageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIES, result)
                }
            }

    }

}
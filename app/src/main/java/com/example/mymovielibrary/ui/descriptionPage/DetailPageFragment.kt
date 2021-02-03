package com.example.mymovielibrary.ui.descriptionPage

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.mymovielibrary.R
import com.example.mymovielibrary.adapter.MovieReviewAdapter
import com.example.mymovielibrary.adapter.MovieTrailerAdapter
import com.example.mymovielibrary.clickListenerInterface.TrailerClickListener
import com.example.mymovielibrary.data.repositoryImplementation.MovieRepositoryImpl
import com.example.mymovielibrary.databse.MovieDatabase
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.viewModelFactory.DetailPageViewModelFactory
import com.google.android.material.appbar.CollapsingToolbarLayout


class DetailPageFragment : Fragment(), TrailerClickListener {

    private var movie: Movie? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val database by lazy { MovieDatabase.getDatabase(requireContext()) }
        val repository by lazy { MovieRepositoryImpl(database.movieDao()) }

        val view = inflater.inflate(R.layout.detail_page_fragment, container, false)

        val viewModel: DetailPageViewModel by viewModels {
            DetailPageViewModelFactory(repository)
        }

        /*val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.detail_toolbar)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)*/

        val extras: Bundle? = requireActivity().intent.getExtras()
        movie = extras?.getParcelable("movie_id")


        val movieTitle = view.findViewById<TextView>(R.id.movie_title)
        val movieReleasingYear = view.findViewById<TextView>(R.id.movie_year)
        val movieRating = view.findViewById<RatingBar>(R.id.movie_rating)
        val movieSynopsis = view.findViewById<TextView>(R.id.text_synopsis)


        movieTitle.text = movie?.title
        movieReleasingYear.text = movie?.release_date
        if (movie != null) {
            movieRating.rating = (movie?.vote_average?.toFloat()?.div(2.0))?.toFloat() ?: 0.0f
        }

        movieSynopsis.text = movie?.overview

        val trailerAdapter = MovieTrailerAdapter(this)
        val recyclerViewTrailer: RecyclerView = view.findViewById(R.id.recycler_view_for_trailers)
        recyclerViewTrailer.adapter = trailerAdapter
        recyclerViewTrailer.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTrailer.smoothScrollBy(0, 0)

        val reviewAdapter = MovieReviewAdapter()
        val recyclerViewReview: RecyclerView = view.findViewById(R.id.recycler_view_for_reviews)
        recyclerViewReview.adapter = reviewAdapter
        recyclerViewReview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewReview.smoothScrollBy(0, 0)

        movie?.id?.let { viewModel.fetchMovieId(it) }

        viewModel.serverResponse.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                Log.d("Response: ", " $it")
            }
        }

        viewModel.reviewProperties.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                reviewAdapter.setReviewData(it)
                Log.d("Response: ", " $it")
            }
        }

        viewModel.trailerProperties.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                trailerAdapter.setTrailerData(it)
                Log.d("Response: ", " $it")
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun onTrailerClickListener(key: String) {
        Log.d("inTrailerClick", "onTrailerClickListener: $key")
        startActivity(Intent(ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + key)))
    }
}

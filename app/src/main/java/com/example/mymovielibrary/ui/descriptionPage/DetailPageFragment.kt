package com.example.mymovielibrary.ui.descriptionPage

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.R
import com.example.mymovielibrary.adapter.MovieReviewAdapter
import com.example.mymovielibrary.adapter.MovieTrailerAdapter
import com.example.mymovielibrary.clickListenerInterface.TrailerClickListener
import com.example.mymovielibrary.data.repositoryImplementation.MovieRepositoryImpl
import com.example.mymovielibrary.databse.MovieDatabase
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.viewModelFactory.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailPageFragment : DaggerFragment(), TrailerClickListener {
    private var movie: Movie? = null

    //Dagger Code
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_page_fragment, container, false)
        val viewModel: DetailPageViewModel by viewModels {
            viewModelFactory
        }

        val extras: Bundle? = requireActivity().intent.extras
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

    override fun onTrailerClickListener(key: String) {
        Log.d("inTrailerClick", "onTrailerClickListener: $key")
        startActivity(Intent(ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$key")))
    }
}

package com.example.mymovielibrary.ui.descriptionPage

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.mymovielibrary.R
import com.example.mymovielibrary.adapter.MovieReviewAdapter
import com.example.mymovielibrary.adapter.MovieTrailerAdapter
import com.example.mymovielibrary.clickListenerInterface.TrailerClickListener
import com.example.mymovielibrary.model.Movie
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailPageFragment : Fragment(), TrailerClickListener {

    private lateinit var movie: Movie
    private lateinit var viewModel: DetailPageViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.detail_page_fragment, container, false)
        viewModel = ViewModelProvider(this).get(DetailPageViewModel::class.java)
        movie = arguments?.get(MOVIES) as Movie

        val moviePicture = view.findViewById<ImageView>(R.id.movie_picture_detail_page)
        val movieTitle = view.findViewById<TextView>(R.id.movie_title)
        val movieReleasingYear = view.findViewById<TextView>(R.id.movie_year)
        val movieRating = view.findViewById<RatingBar>(R.id.movie_rating)
        val movieSynopsis = view.findViewById<TextView>(R.id.text_synopsis)
        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.floting_action_button)
        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                R.drawable.favorite_border));

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185" + movie.poster_path)
                .apply(
                        RequestOptions()
                                .override(Target.SIZE_ORIGINAL, 800)
                )
                .into(moviePicture)

        movieTitle.text = movie.title
        movieReleasingYear.text = movie.release_date
        movieRating.rating = (movie.vote_average.toFloat() / 2.0).toFloat()
        movieSynopsis.text = movie.overview

        val trailerAdapter = MovieTrailerAdapter(this)
        val recyclerViewTrailer: RecyclerView = view.findViewById(R.id.recycler_view_for_trailers)
        recyclerViewTrailer.adapter = trailerAdapter
        recyclerViewTrailer.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTrailer.smoothScrollBy(0, 0)

        val reviewAdapter = MovieReviewAdapter()
        val recyclerViewReview: RecyclerView = view.findViewById(R.id.recycler_view_for_reviews)
        recyclerViewReview.adapter = reviewAdapter
        recyclerViewReview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewReview.smoothScrollBy(0, 0)

        viewModel.fetchMovieId(movie.id)

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

        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)

        activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = movie.title
    }

    companion object {
        const val MOVIES = "MOVIES"
        fun build(result: Movie) = DetailPageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIES, result)
            }
        }

    }

    override fun onTrailerClickListener(key: String) {
        Log.d("inTrailerClick", "onTrailerClickListener: $key")
        startActivity(Intent(ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + key)))
    }
}

package com.example.mymovielibrary.ui.descriptionPage

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.adapter.MovieReviewAdapter
import com.example.mymovielibrary.adapter.MovieTrailerAdapter
import com.example.mymovielibrary.clickListenerInterface.TrailerClickListener
import com.example.mymovielibrary.databinding.DetailPageFragmentBinding
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.viewModelFactory.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailPageFragment : DaggerFragment(), TrailerClickListener {
    private var movie: Movie? = null

    //Dagger Code
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _detailPageFragmentBinding: DetailPageFragmentBinding? = null
    private val detailPageFragmentBinding get() = _detailPageFragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //View Binding
        _detailPageFragmentBinding = DetailPageFragmentBinding.inflate(inflater, container, false)
        val view = detailPageFragmentBinding.root

        val viewModel: DetailPageViewModel by viewModels {
            viewModelFactory
        }

        val extras: Bundle? = requireActivity().intent.extras
        movie = extras?.getParcelable("movie_id")

        detailPageFragmentBinding.movieTitle.text = movie?.title
        detailPageFragmentBinding.movieYear.text = movie?.release_date
        if (movie != null) {
            detailPageFragmentBinding.movieRating.rating =
                (movie?.vote_average?.toFloat()?.div(2.0))?.toFloat() ?: 0.0f
        }

        detailPageFragmentBinding.textSynopsis.text = movie?.overview

        val trailerAdapter = MovieTrailerAdapter(this)
        val recyclerViewTrailer: RecyclerView = detailPageFragmentBinding.recyclerViewForTrailers
        recyclerViewTrailer.adapter = trailerAdapter
        recyclerViewTrailer.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTrailer.smoothScrollBy(0, 0)

        val reviewAdapter = MovieReviewAdapter()
        val recyclerViewReview: RecyclerView = detailPageFragmentBinding.recyclerViewForReviews
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

package com.example.mymovielibrary

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.mymovielibrary.databinding.ActivityMovieDetailPageBinding
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.ui.descriptionPage.DetailPageViewModel
import com.example.mymovielibrary.viewModelFactory.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailPageActivity : DaggerAppCompatActivity() {

    //Dagger Code
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var detailPageBinding: ActivityMovieDetailPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailPageBinding = ActivityMovieDetailPageBinding.inflate(layoutInflater)
        setContentView(detailPageBinding.root)

        val movie = intent.getParcelableExtra<Movie>("movie_id")

        // ActionBar Code
        setSupportActionBar(detailPageBinding.detailToolbar)
        detailPageBinding.toolbarLayout.title = movie?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val viewModel: DetailPageViewModel by viewModels {
            viewModelFactory
        }

        movie?.let { viewModel.movieFetch(it) }

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w185" + movie?.poster_path)
            .apply(
                RequestOptions()
                    .override(Target.SIZE_ORIGINAL, 800)
            )
            .into(detailPageBinding.moviePictureDetailPage)

        detailPageBinding.floatingActionButton.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.favorite_border
            )
        )

        viewModel.isMovieExist.observe(this) {
            if (it)
                detailPageBinding.floatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.favorite_fill
                    )
                )
            else
                detailPageBinding.floatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.favorite_border
                    )
                )
        }

        detailPageBinding.floatingActionButton.setOnClickListener {
            movie?.let { viewModel.handledInsertAndDelete(it) }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

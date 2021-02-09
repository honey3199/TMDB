package com.example.mymovielibrary

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.ui.descriptionPage.DetailPageViewModel
import com.example.mymovielibrary.viewModelFactory.ViewModelFactory
import com.google.android.material.appbar.CollapsingToolbarLayout
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailPageActivity : DaggerAppCompatActivity() {

    //Dagger Code
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail_page)

        val movie = intent.getParcelableExtra<Movie>("movie_id")

        // ActionBar Code
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.detail_toolbar)
        setSupportActionBar(toolbar)
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = movie?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        val viewModel: DetailPageViewModel by viewModels {
            viewModelFactory
        }

        movie?.let { viewModel.movieFetch(it) }

        val moviePicture = findViewById<ImageView>(R.id.movie_picture_detail_page)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w185" + movie?.poster_path)
            .apply(
                RequestOptions()
                    .override(Target.SIZE_ORIGINAL, 800)
            )
            .into(moviePicture)

        val floatingActionButton = findViewById<ImageButton>(R.id.floating_action_button)

        floatingActionButton.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.favorite_border
            )
        );

        viewModel.isMovieExist.observe(this) {
            if (it)
                floatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.favorite_fill
                    )
                )
            else
                floatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.favorite_border
                    )
                )
        }

        floatingActionButton.setOnClickListener {
            movie?.let { viewModel.handledInsertAndDelete(it) }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

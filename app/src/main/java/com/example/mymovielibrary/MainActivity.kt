package com.example.mymovielibrary

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MovieLibraryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MovieLibraryViewModel::class.java)

        val movieAdapter = MovieLibraryAdapter()
        val recyclerView : RecyclerView = findViewById(R.id.recycler_view_movie_list)
        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.smoothScrollBy(0, 0)

        viewModel.serverResponse.observe(this) {
            if (!it.isNullOrEmpty()) {
                Log.d("Response: ", " $it")
            }
        }

        viewModel.property.observe(this) {
            if (!it.isNullOrEmpty()) {
                movieAdapter.setData(it)
                Log.d("Response: ", " $it")
            }
        }
    }
}
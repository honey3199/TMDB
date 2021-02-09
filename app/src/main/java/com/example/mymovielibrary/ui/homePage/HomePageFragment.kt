package com.example.mymovielibrary.ui.homePage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.DetailPageActivity
import com.example.mymovielibrary.R
import com.example.mymovielibrary.adapter.HomePageAdapter
import com.example.mymovielibrary.clickListenerInterface.MovieClickListener
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.viewModelFactory.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomePageFragment : DaggerFragment(), MovieClickListener {
    private lateinit var viewModel: HomePageViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.findViewById<Toolbar>(R.id.toolbar_layout)?.title = getString(R.string.app_name)

        val view = inflater.inflate(R.layout.home_page_fragment, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(HomePageViewModel::class.java)
        viewModel.fetchMovies()

        val movieAdapter = HomePageAdapter(this)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_movie_list)
        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager = GridLayoutManager(view.context, 2)
        recyclerView.smoothScrollBy(0, 0)

        viewModel.serverResponse.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                Log.d("Response: ", " $it")
            }
        }

        viewModel.movieProperties.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                movieAdapter.setMovieData(it)
                Log.d("Response: ", " $it")
            }
        }
        return view
    }

    override fun onMovieClickListener(result: Movie) {
        val intent = Intent(this.activity, DetailPageActivity::class.java)
        val mBundle = Bundle()
        mBundle.putParcelable("movie_id", result)
        intent.putExtras(mBundle)
        startActivity(intent)
    }
}

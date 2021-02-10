package com.example.mymovielibrary.ui.homePage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.DetailPageActivity
import com.example.mymovielibrary.adapter.HomePageAdapter
import com.example.mymovielibrary.clickListenerInterface.MovieClickListener
import com.example.mymovielibrary.databinding.LayoutHomePageBinding
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.viewModelFactory.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomePageFragment : DaggerFragment(), MovieClickListener {
    private lateinit var viewModel: HomePageViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _homePageFragmentBinding: LayoutHomePageBinding? = null
    private val homePageFragmentBinding get() = _homePageFragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _homePageFragmentBinding = LayoutHomePageBinding.inflate(inflater, container, false)
        val view = homePageFragmentBinding.root

        viewModel = ViewModelProvider(this, viewModelFactory).get(HomePageViewModel::class.java)
        viewModel.fetchMovies()

        val movieAdapter = HomePageAdapter(this)
        val recyclerView: RecyclerView = homePageFragmentBinding.recyclerViewMovieList
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

    override fun onDestroyView() {
        super.onDestroyView()
        _homePageFragmentBinding = null
    }

    override fun onMovieClickListener(result: Movie) {
        val intent = Intent(this.activity, DetailPageActivity::class.java)
        val mBundle = Bundle()
        mBundle.putParcelable("movie_id", result)
        intent.putExtras(mBundle)
        startActivity(intent)
    }
}

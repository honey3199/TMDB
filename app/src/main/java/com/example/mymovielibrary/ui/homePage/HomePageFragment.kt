package com.example.mymovielibrary.ui.homePage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielibrary.R
import com.example.mymovielibrary.adapter.MovieLibraryAdapter

class HomePageFragment : Fragment() {
    private lateinit var viewModel: HomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_page_fragment, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(HomePageViewModel::class.java)
        val movieAdapter = MovieLibraryAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_movie_list)
        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager = GridLayoutManager(view.context, 2)
        recyclerView.smoothScrollBy(0, 0)

        viewModel.serverResponse.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                Log.d("Response: ", " $it")
            }
        }

        viewModel.property.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                movieAdapter.setData(it)
                Log.d("Response: ", " $it")
            }
        }
        return view
    }
}

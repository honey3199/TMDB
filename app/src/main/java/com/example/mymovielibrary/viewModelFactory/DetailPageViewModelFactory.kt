package com.example.mymovielibrary.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymovielibrary.data.repository.MovieRepository
import com.example.mymovielibrary.ui.descriptionPage.DetailPageViewModel

class DetailPageViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailPageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailPageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

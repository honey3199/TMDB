package com.example.mymovielibrary.di.modules

import com.example.mymovielibrary.data.repository.MovieRepository
import com.example.mymovielibrary.data.repositoryImplementation.MovieRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl):MovieRepository
}

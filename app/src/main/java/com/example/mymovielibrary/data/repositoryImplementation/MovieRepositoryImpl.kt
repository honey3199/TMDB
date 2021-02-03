package com.example.mymovielibrary.data.repositoryImplementation

import com.example.mymovielibrary.dao.MovieDao
import com.example.mymovielibrary.data.repository.MovieRepository
import com.example.mymovielibrary.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(val movieDao: MovieDao) : MovieRepository {
    override suspend fun insertMovieInMovieEntity(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.insertMovie(movie)
    }
}
package com.example.mymovielibrary.data.repositoryImplementation

import com.example.mymovielibrary.dao.MovieDao
import com.example.mymovielibrary.data.repository.MovieRepository
import com.example.mymovielibrary.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(private val movieDao: MovieDao) : MovieRepository {
    override suspend fun insertMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.insertMovie(movie)
    }

    override suspend fun checkMovieExist(movieId: Int): Movie? = withContext(Dispatchers.IO) {
        return@withContext movieDao.isMovieExists(movieId)
    }

    override suspend fun deleteMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.deleteMovie(movie)
    }
}

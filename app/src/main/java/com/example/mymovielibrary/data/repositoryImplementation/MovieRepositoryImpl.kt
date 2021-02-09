package com.example.mymovielibrary.data.repositoryImplementation

import com.example.mymovielibrary.dao.MovieDao
import com.example.mymovielibrary.data.repository.MovieRepository
import com.example.mymovielibrary.model.Movie
import com.example.mymovielibrary.model.MovieProperties
import com.example.mymovielibrary.service.MovieApiService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val api: MovieApiService
) : MovieRepository {
    override suspend fun insertMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.insertMovie(movie)
    }

    override suspend fun checkMovieExist(movieId: Int): Movie? = withContext(Dispatchers.IO) {
        return@withContext movieDao.isMovieExists(movieId)
    }

    override suspend fun deleteMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.deleteMovie(movie)
    }

    override suspend fun fetchAllMovie(key: String): Response<MovieProperties> =
        withContext(Dispatchers.IO) {
           return@withContext api.getAllMovies(key)
        }


}

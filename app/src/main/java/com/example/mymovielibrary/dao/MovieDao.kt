package com.example.mymovielibrary.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymovielibrary.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie_entity WHERE id = :movieId")
    fun isMovieExists(movieId: Int): Movie

    @Delete
    fun deleteMovie(movie: Movie)
}
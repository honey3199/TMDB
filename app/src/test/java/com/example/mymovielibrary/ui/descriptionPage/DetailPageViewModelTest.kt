package com.example.mymovielibrary.ui.descriptionPage

import com.example.mymovielibrary.data.repository.MovieRepository
import com.example.mymovielibrary.model.Movie
import com.google.common.truth.Truth
import io.mockk.MockK
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class DetailPageViewModelTest {

    lateinit var viewModel: DetailPageViewModel

    var repository: MovieRepository = mockk()

    @BeforeEach
    fun setUp() {
        viewModel = DetailPageViewModel(repository = repository)
    }

    @Test
    fun `_isMovieExist will have false value if checkMovieExist() returns null`()  {
        val movie = createMovieObject()
        coEvery { repository.checkMovieExist(movie.id) } returns null
        viewModel.movieFetch(movie)
        val bool = viewModel.isMovieExist.value
        Truth.assertThat(bool).isEqualTo(false)
    }

    @Test
    fun `_isMovieExist will have false value if checkMovieExist() returns not null`()  {
        val movie = createMovieObject()
        coEvery { repository.checkMovieExist(movie.id) } returns movie
        viewModel.movieFetch(movie)
        val bool = viewModel.isMovieExist.value
        Truth.assertThat(bool).isEqualTo(true)
    }

    private fun createMovieObject(): Movie {
        return Movie(
            true,
            "/honey",
            1,
            "English",
            "Honey",
            "Movie ",
            1.0,
            "/akdfogd",
            "",
            "Honey B",
            true,
            1.0,
            1
        )
    }

}
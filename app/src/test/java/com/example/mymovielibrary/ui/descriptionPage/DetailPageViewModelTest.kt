package com.example.mymovielibrary.ui.descriptionPage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mymovielibrary.data.repository.MovieRepository
import com.example.mymovielibrary.getOrAwaitValue
import com.example.mymovielibrary.model.Movie
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.rules.TestRule


@ExperimentalCoroutinesApi
internal class DetailPageViewModelTest {

    lateinit var viewModel: DetailPageViewModel

    var repository: MovieRepository = mockk()

    private val dispatcher = TestCoroutineDispatcher()

    @Rule
    var rule: TestRule? = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = DetailPageViewModel(repository = repository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `_isMovieExist will have false value if checkMovieExist() returns null`() = runBlockingTest {
        val movie = createMovieObject()
        coEvery { repository.checkMovieExist(movie.id) } returns null
        viewModel.movieFetch(movie)
        val bool = viewModel.isMovieExist.getOrAwaitValue()
        Truth.assertThat(bool).isEqualTo(false)
    }

    @Test
    fun `_isMovieExist will have false value if checkMovieExist() returns not null`() {
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
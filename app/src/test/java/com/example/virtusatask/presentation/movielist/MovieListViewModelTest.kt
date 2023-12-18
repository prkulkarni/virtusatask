package com.example.virtusatask.presentation.movielist

import com.example.virtusatask.BaseTest
import com.example.virtusatask.domain.model.MovieItem
import com.example.virtusatask.domain.model.MovieList
import com.example.virtusatask.domain.usecase.movielist.GetMovieListUseCase
import com.example.virtusatask.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest : BaseTest() {

    private lateinit var movieListViewModel: MovieListViewModel

    private val movieListUseCase: GetMovieListUseCase = mockk()
    private val movieList: MovieList = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        coEvery { movieListUseCase() } returns flow {
            emit(Result.Loading())
        }
        movieListViewModel = MovieListViewModel(movieListUseCase)
    }

    @Test
    fun getMovieList_returnsLoading() = runTest {
        coEvery { movieListUseCase() } returns flow {
            emit(Result.Loading())
        }
        movieListViewModel.getMovieList()
        advanceUntilIdle()
        coVerify { movieListUseCase() }
        assertTrue(movieListViewModel.state.value.isLoading)
    }

    @Test
    fun getMovieList_returnsSuccess() = runTest {
        coEvery { movieList.movieList } returns listOf(
            MovieItem(
                id = "123",
                title = "Title",
                imageUrl = "imageUrl"
            )
        )
        coEvery { movieListUseCase() } returns flow {
            emit(Result.Success(movieList))
        }
        movieListViewModel.getMovieList()
        advanceUntilIdle()
        coVerify { movieListUseCase() }
        assertTrue(movieListViewModel.state.value.movieList.movieList.size == 1)
    }

    @Test
    fun getMovieList_returnsError() = runTest {
        coEvery { movieListUseCase() } returns flow {
            emit(Result.Error(message = DEFAULT_ERROR_MESSAGE))
        }
        movieListViewModel.getMovieList()
        advanceUntilIdle()
        coVerify { movieListUseCase() }
        assertTrue(movieListViewModel.state.value.errorMessage == DEFAULT_ERROR_MESSAGE)
    }
}
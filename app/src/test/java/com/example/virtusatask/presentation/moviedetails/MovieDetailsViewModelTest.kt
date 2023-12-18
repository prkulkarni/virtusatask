package com.example.virtusatask.presentation.moviedetails

import androidx.lifecycle.SavedStateHandle
import com.example.virtusatask.BaseTest
import com.example.virtusatask.domain.model.MovieDetails
import com.example.virtusatask.domain.usecase.moviedetails.GetMovieDetailsUseCase
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
class MovieDetailsViewModelTest : BaseTest() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    private val movieDetailsUseCase: GetMovieDetailsUseCase = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        coEvery { movieDetailsUseCase(MOVIE_ID_VAL) } returns flow {
            emit(Result.Loading())
        }
        coEvery { savedStateHandle.get<String>(MOVIE_ID_KEY) } returns MOVIE_ID_VAL
        movieDetailsViewModel = MovieDetailsViewModel(movieDetailsUseCase, savedStateHandle)
    }

    @Test
    fun getMovieDetails_returnsLoading() = runTest {
        coEvery { movieDetailsUseCase(MOVIE_ID_VAL) } returns flow {
            emit(Result.Loading())
        }

        movieDetailsViewModel.getMovieDetails(MOVIE_ID_VAL)
        advanceUntilIdle()
        coVerify { movieDetailsUseCase(MOVIE_ID_VAL) }
        assertTrue(movieDetailsViewModel.state.value.isLoading)
    }

    @Test
    fun getMovieDetails_returnsSuccess() = runTest {
        coEvery { movieDetailsUseCase(MOVIE_ID_VAL) } returns flow {
            emit(
                Result.Success(
                    MovieDetails(
                        imageUrl = "imageUrl",
                        fullTitle = "title",
                        plot = "plot"
                    )
                )
            )
        }

        movieDetailsViewModel.getMovieDetails(MOVIE_ID_VAL)
        advanceUntilIdle()
        coVerify { movieDetailsUseCase(MOVIE_ID_VAL) }
        assertTrue(movieDetailsViewModel.state.value.movieDetails != null)
    }

    @Test
    fun getMovieDetails_returnsError() = runTest {
        coEvery { movieDetailsUseCase(MOVIE_ID_VAL) } returns flow {
            emit(
                Result.Error(
                    message = DEFAULT_ERROR_MESSAGE
                )
            )
        }

        movieDetailsViewModel.getMovieDetails(MOVIE_ID_VAL)
        advanceUntilIdle()
        coVerify { movieDetailsUseCase(MOVIE_ID_VAL) }
        assertTrue(movieDetailsViewModel.state.value.errorMessage == DEFAULT_ERROR_MESSAGE)
    }
}
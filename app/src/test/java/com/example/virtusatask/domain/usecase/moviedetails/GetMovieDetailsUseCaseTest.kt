package com.example.virtusatask.domain.usecase.moviedetails

import com.example.virtusatask.BaseTest
import com.example.virtusatask.data.dto.MovieDetailsDto
import com.example.virtusatask.data.dto.toMovieDetails
import com.example.virtusatask.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieDetailsUseCaseTest : BaseTest() {
    private lateinit var movieDetailsUseCase: GetMovieDetailsUseCase

    private val repository: MovieRepository = mockk()
    private val movieDetailsDto: MovieDetailsDto = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        movieDetailsUseCase = GetMovieDetailsUseCase(repository)
    }

    @Test
    fun getMovieDetails_onSuccess_returnsMovieDetails() = runTest {
        coEvery { movieDetailsDto.image } returns "image"
        coEvery { movieDetailsDto.fullTitle } returns "title"
        coEvery { movieDetailsDto.plot } returns "plot"
        coEvery { repository.getMovieDetails(MOVIE_ID_VAL) } returns movieDetailsDto
        movieDetailsUseCase(MOVIE_ID_VAL)
        advanceUntilIdle()
        val result = repository.getMovieDetails(MOVIE_ID_VAL)
        assertTrue(result.toMovieDetails().imageUrl == "image")
        assertTrue(result.toMovieDetails().fullTitle == "title")
        assertTrue(result.toMovieDetails().plot == "plot")
    }

    @Test(expected = Exception::class)
    fun getMovieDetails_onError_returnsErrorMessage() = runTest {
        coEvery { repository.getMovieDetails(MOVIE_ID_VAL) } throws (Exception())
        movieDetailsUseCase(MOVIE_ID_VAL)
        advanceUntilIdle()
        val result = repository.getMovieDetails(MOVIE_ID_VAL)
        assertTrue(result.errorMessage == DEFAULT_ERROR_MESSAGE)
    }
}
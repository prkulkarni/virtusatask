package com.example.virtusatask.data.repository

import com.example.virtusatask.BaseTest
import com.example.virtusatask.data.dto.MovieDetailsDto
import com.example.virtusatask.data.dto.MovieListDto
import com.example.virtusatask.data.remote.MovieApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest : BaseTest() {
    private lateinit var movieRepository: MovieRepositoryImpl

    private val movieApi: MovieApi = mockk()
    private val movieListDto: MovieListDto = mockk()
    private val movieDetailsDto: MovieDetailsDto = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        movieRepository = MovieRepositoryImpl(movieApi)
    }

    @Test
    fun getMovieListTest() = runTest {
        coEvery { movieApi.getMoviesList(any(), any()) } returns movieListDto
        movieRepository.getMovieList()

        coVerify { movieApi.getMoviesList() }
    }

    @Test
    fun getMovieDetailsTest() = runTest {
        coEvery { movieApi.getMovieDetails(any(), any(), any()) } returns movieDetailsDto
        movieRepository.getMovieDetails(MOVIE_ID_VAL)

        coVerify { movieApi.getMovieDetails(MOVIE_ID_VAL) }
    }

}
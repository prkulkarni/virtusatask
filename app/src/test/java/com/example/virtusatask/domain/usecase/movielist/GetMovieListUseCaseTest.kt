package com.example.virtusatask.domain.usecase.movielist

import com.example.virtusatask.BaseTest
import com.example.virtusatask.data.dto.MovieListDto
import com.example.virtusatask.domain.model.MovieItem
import com.example.virtusatask.domain.model.MovieList
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
class GetMovieListUseCaseTest : BaseTest() {

    private lateinit var movieListUseCase: GetMovieListUseCase

    private val repository: MovieRepository = mockk()
    private val movieListDto: MovieListDto = mockk()
    private val movieList: MovieList = mockk()
    private val movieItem: MovieItem = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        movieListUseCase = GetMovieListUseCase(repository)
    }

    @Test
    fun getMovieList_onSuccess_returnsMovieList() = runTest {
        coEvery { movieList.movieList } returns listOf(movieItem)
        coEvery { movieListDto.toMovieList() } returns movieList
        coEvery { repository.getMovieList() } returns movieListDto
        movieListUseCase()
        advanceUntilIdle()
        val result = repository.getMovieList()
        assertTrue(result.toMovieList().movieList.isNotEmpty())
    }

    @Test(expected = Exception::class)
    fun getMovieList_onError_returnsErrorMessage() = runTest {
        coEvery { repository.getMovieList() } throws (Exception())
        movieListUseCase()
        advanceUntilIdle()
        val result = repository.getMovieList()
        assertTrue(result.errorMessage == DEFAULT_ERROR_MESSAGE)
    }
}
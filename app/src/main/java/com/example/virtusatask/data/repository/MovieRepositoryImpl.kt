package com.example.virtusatask.data.repository

import com.example.virtusatask.data.dto.MovieDetailsDto
import com.example.virtusatask.data.dto.MovieListDto
import com.example.virtusatask.data.remote.MovieApi
import com.example.virtusatask.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun getMovieList(): MovieListDto {
        return movieApi.getMoviesList()
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetailsDto {
        return movieApi.getMovieDetails(movieId)
    }
}
package com.example.virtusatask.domain.repository

import com.example.virtusatask.data.dto.MovieDetailsDto
import com.example.virtusatask.data.dto.MovieListDto

interface MovieRepository {

    suspend fun getMovieList(): MovieListDto

    suspend fun getMovieDetails(movieId: String): MovieDetailsDto
}
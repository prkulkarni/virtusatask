package com.example.virtusatask.domain.usecase.moviedetails

import com.example.virtusatask.constants.Constants
import com.example.virtusatask.data.dto.toMovieDetails
import com.example.virtusatask.domain.model.MovieDetails
import com.example.virtusatask.domain.repository.MovieRepository
import com.example.virtusatask.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: String): Flow<Result<MovieDetails>> = flow {
        try {
            emit(Result.Loading())
            val movieDetails = movieRepository.getMovieDetails(movieId).toMovieDetails()
            emit(Result.Success(data = movieDetails))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: Constants.DEFAULT_ERROR_MESSAGE))
        }
    }
}
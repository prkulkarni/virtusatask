package com.example.virtusatask.domain.usecase.movielist

import com.example.virtusatask.constants.Constants.DEFAULT_ERROR_MESSAGE
import com.example.virtusatask.domain.model.MovieList
import com.example.virtusatask.domain.repository.MovieRepository
import com.example.virtusatask.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Result<MovieList>> = flow {
        try {
            emit(Result.Loading())
            val movieList = movieRepository.getMovieList().toMovieList()
            emit(Result.Success(data = movieList))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: DEFAULT_ERROR_MESSAGE))
        }
    }
}
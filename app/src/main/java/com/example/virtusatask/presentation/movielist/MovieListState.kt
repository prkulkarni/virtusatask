package com.example.virtusatask.presentation.movielist

import com.example.virtusatask.constants.Constants.DEFAULT_ERROR_MESSAGE
import com.example.virtusatask.domain.model.MovieList

data class MovieListState(
    val isLoading: Boolean = false,
    val movieList: MovieList = MovieList(emptyList(), DEFAULT_ERROR_MESSAGE),
    val errorMessage: String = ""
)

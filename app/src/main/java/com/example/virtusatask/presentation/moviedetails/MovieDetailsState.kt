package com.example.virtusatask.presentation.moviedetails

import com.example.virtusatask.domain.model.MovieDetails

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movieDetails: MovieDetails? = null,
    val errorMessage: String = ""
)

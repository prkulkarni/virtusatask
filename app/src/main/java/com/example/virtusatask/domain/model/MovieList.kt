package com.example.virtusatask.domain.model

data class MovieList(
    val movieList: List<MovieItem>,
    val errorMessage: String?
)
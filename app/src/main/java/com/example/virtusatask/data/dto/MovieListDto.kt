package com.example.virtusatask.data.dto

import com.example.virtusatask.domain.model.MovieItem
import com.example.virtusatask.domain.model.MovieList

data class MovieListDto(
    val items: List<MovieItemDto>?,
    val errorMessage: String?
) {
    fun toMovieList(): MovieList {
        val movieItemList = mutableListOf<MovieItem>()

        items?.forEach {
            movieItemList.add(it.toMovieItem())
        }
        return MovieList(movieItemList, errorMessage)

    }
}

data class MovieItemDto(
    val id: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val releaseState: String,
    val runtimeMins: String?,
    val runtimeStr: String?,
    val plot: String?,
    val contentRating: String?,
    val imDbRating: String?,
    val imDbRatingCount: String?,
    val metacriticRating: String?,
    val genres: String,
    val genreList: List<KeyValueItem>,
    val directors: String?,
    val directorList: List<StarItem>,
    val stars: String,
    val starList: List<StarItem>
) {
    fun toMovieItem(): MovieItem {
        return MovieItem(id, title, image)
    }
}

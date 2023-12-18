package com.example.virtusatask.presentation.movielist

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.virtusatask.constants.Constants.DEFAULT_ERROR_MESSAGE
import com.example.virtusatask.domain.model.MovieList
import com.example.virtusatask.domain.usecase.movielist.GetMovieListUseCase
import com.example.virtusatask.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: GetMovieListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state

    init {
        getMovieList()
    }

    @VisibleForTesting
    fun getMovieList() {
        movieListUseCase().onEach { result ->
            when (result) {
                is Result.Success -> {
                    _state.value = MovieListState(
                        movieList = result.data ?: MovieList(
                            emptyList(),
                            DEFAULT_ERROR_MESSAGE
                        )
                    )
                }

                is Result.Error -> {
                    _state.value = MovieListState(
                        errorMessage = result.data?.errorMessage ?: DEFAULT_ERROR_MESSAGE
                    )
                }

                is Result.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
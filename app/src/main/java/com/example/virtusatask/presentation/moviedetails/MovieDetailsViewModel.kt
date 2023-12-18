package com.example.virtusatask.presentation.moviedetails

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.virtusatask.constants.Constants.DEFAULT_ERROR_MESSAGE
import com.example.virtusatask.constants.Constants.MOVIE_ID
import com.example.virtusatask.domain.usecase.moviedetails.GetMovieDetailsUseCase
import com.example.virtusatask.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailsState())
    val state: State<MovieDetailsState> = _state

    init {
        savedStateHandle.get<String>(MOVIE_ID)?.let { movieId ->
            getMovieDetails(movieId)
        }
    }

    @VisibleForTesting
    fun getMovieDetails(movieId: String) {
        movieDetailsUseCase(movieId).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _state.value = MovieDetailsState(
                        movieDetails = result.data
                    )
                }

                is Result.Error -> {
                    _state.value = MovieDetailsState(
                        errorMessage = result.message ?: DEFAULT_ERROR_MESSAGE
                    )
                }

                is Result.Loading -> {
                    _state.value = MovieDetailsState(
                        isLoading = true
                    )
                }
            }

        }.launchIn(viewModelScope)
    }
}
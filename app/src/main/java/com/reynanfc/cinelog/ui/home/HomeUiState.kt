package com.reynanfc.cinelog.ui.home

import com.reynanfc.cinelog.domain.model.Movie

data class HomeUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val popularMovies: List<Movie> = emptyList(),
    val nowPlayingMovies: List<Movie> = emptyList(),
    val errorMessage: String? = null
)

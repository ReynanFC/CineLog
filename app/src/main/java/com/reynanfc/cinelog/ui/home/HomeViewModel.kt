package com.reynanfc.cinelog.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reynanfc.cinelog.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadMovies(isRefresh = false)
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnRetryClicked -> loadMovies(isRefresh = false)
            is HomeUiEvent.OnRefresh -> loadMovies(isRefresh = true)
        }
    }

    private fun loadMovies(isRefresh: Boolean) {
        viewModelScope.launch {

            if (isRefresh) {
                _uiState.update { it.copy(isRefreshing = true, errorMessage = null) }
            } else {
                _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            }

            try {
                val popular = movieRepository.getPopularMovies()
                val nowPlaying = movieRepository.getNowPlayingMovies()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        popularMovies = popular,
                        nowPlayingMovies = nowPlaying
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        errorMessage = "Não foi possível carregar os filmes") }
            }
        }
    }}
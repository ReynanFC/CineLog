package com.reynanfc.cinelog.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reynanfc.cinelog.domain.repository.MovieRepository
import com.reynanfc.cinelog.ui.search.contract.SearchUiEvent
import com.reynanfc.cinelog.ui.search.contract.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SEARCH_DEBOUNCE_MILLIS = 400L

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        searchMovies(shouldDebounce = false)
    }

    fun onEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.OnQueryChange -> {
                _uiState.update { it.copy(query = event.query) }
                searchMovies(shouldDebounce = true)
            }

            is SearchUiEvent.OnCategorySelected -> {
                _uiState.update { it.copy(selectedCategory = event.category) }
                searchMovies(shouldDebounce = false)
            }

            is SearchUiEvent.OnSearch -> searchMovies(shouldDebounce = false)
            is SearchUiEvent.OnRetryClicked -> searchMovies(shouldDebounce = false)
        }
    }

    private fun searchMovies(shouldDebounce: Boolean) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (shouldDebounce) delay(SEARCH_DEBOUNCE_MILLIS)

            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val currentState = _uiState.value
            try {
                val movies = movieRepository.searchMovies(
                    query = currentState.query,
                    genreId = currentState.selectedCategory.genreId
                )
                _uiState.update { it.copy(isLoading = false, movies = movies) }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        movies = emptyList(),
                        errorMessage = "Não foi possível carregar os resultados. Verifique sua conexão e tente novamente."
                    )
                }
            }
        }
    }
}

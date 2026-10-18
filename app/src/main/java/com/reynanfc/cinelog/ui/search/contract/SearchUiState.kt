package com.reynanfc.cinelog.ui.search.contract

import com.reynanfc.cinelog.domain.model.Movie
import com.reynanfc.cinelog.ui.search.model.SearchCategory

data class SearchUiState(
    val query: String = "",
    val selectedCategory: SearchCategory = SearchCategory.ALL,
    val isLoading: Boolean = true,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null
) {
    val isEmpty: Boolean
        get() = !isLoading && errorMessage == null && movies.isEmpty()
}

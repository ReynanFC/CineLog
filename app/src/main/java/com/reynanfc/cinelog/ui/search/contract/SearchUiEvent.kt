package com.reynanfc.cinelog.ui.search.contract

import com.reynanfc.cinelog.ui.search.model.SearchCategory

sealed interface SearchUiEvent {
    data class OnQueryChange(val query: String) : SearchUiEvent
    data class OnCategorySelected(val category: SearchCategory) : SearchUiEvent
    data object OnSearch : SearchUiEvent
    data object OnRetryClicked : SearchUiEvent
}

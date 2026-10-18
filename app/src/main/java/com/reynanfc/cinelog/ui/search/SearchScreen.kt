package com.reynanfc.cinelog.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.reynanfc.cinelog.domain.model.Movie
import com.reynanfc.cinelog.ui.search.components.CategoryFilterChips
import com.reynanfc.cinelog.ui.search.components.SearchEmptyMessage
import com.reynanfc.cinelog.ui.search.components.SearchErrorMessage
import com.reynanfc.cinelog.ui.search.components.SearchInput
import com.reynanfc.cinelog.ui.search.components.SearchResultGrid
import com.reynanfc.cinelog.ui.search.contract.SearchUiEvent
import com.reynanfc.cinelog.ui.search.contract.SearchUiState
import com.reynanfc.cinelog.ui.search.model.SearchCategory
import com.reynanfc.cinelog.ui.theme.CineLogTheme

@Composable
fun SearchScreen(
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SearchContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onMovieClick = onMovieClick,
        modifier = modifier
    )
}

@Composable
private fun SearchContent(
    uiState: SearchUiState,
    onEvent: (SearchUiEvent) -> Unit,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Buscar",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SearchInput(
            query = uiState.query,
            onQueryChange = { query -> onEvent(SearchUiEvent.OnQueryChange(query)) },
            onSearch = {
                focusManager.clearFocus()
                onEvent(SearchUiEvent.OnSearch)
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        CategoryFilterChips(
            selectedCategory = uiState.selectedCategory,
            onCategorySelected = { category -> onEvent(SearchUiEvent.OnCategorySelected(category)) }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.Center)
                )

                uiState.errorMessage != null -> SearchErrorMessage(
                    message = uiState.errorMessage,
                    onRetryClick = { onEvent(SearchUiEvent.OnRetryClicked) }
                )

                uiState.isEmpty -> SearchEmptyMessage()

                else -> SearchResultGrid(
                    movies = uiState.movies,
                    onMovieClick = { movie -> onMovieClick(movie.id) }
                )
            }
        }
    }
}

private val previewMovies = listOf(
    Movie(1, "Interestelar", "", null, null, "2014-11-05", 8.7),
    Movie(2, "Duna: Parte Dois", "", null, null, "2024-02-27", 8.5),
    Movie(3, "A Chegada", "", null, null, "2016-11-24", 7.6),
    Movie(4, "Blade Runner 2049", "", null, null, "2017-10-05", 7.5)
)

@Preview(showBackground = true, name = "Resultados")
@Composable
private fun SearchScreenResultsPreview() {
    CineLogTheme {
        SearchContent(
            uiState = SearchUiState(isLoading = false, movies = previewMovies),
            onEvent = {},
            onMovieClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Lista vazia")
@Composable
private fun SearchScreenEmptyPreview() {
    CineLogTheme {
        SearchContent(
            uiState = SearchUiState(
                query = "xyz",
                selectedCategory = SearchCategory.HORROR,
                isLoading = false
            ),
            onEvent = {},
            onMovieClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Erro")
@Composable
private fun SearchScreenErrorPreview() {
    CineLogTheme {
        SearchContent(
            uiState = SearchUiState(
                isLoading = false,
                errorMessage = "Não foi possível carregar os resultados. Verifique sua conexão e tente novamente."
            ),
            onEvent = {},
            onMovieClick = {}
        )
    }
}

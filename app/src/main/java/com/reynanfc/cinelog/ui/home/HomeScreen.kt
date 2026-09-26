package com.reynanfc.cinelog.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.reynanfc.cinelog.domain.model.Movie
import com.reynanfc.cinelog.ui.components.CineLogBottomBar
import com.reynanfc.cinelog.ui.components.CineLogOptionsBottomSheet
import com.reynanfc.cinelog.ui.components.CineLogTopBar
import com.reynanfc.cinelog.ui.home.components.MovieSection
import com.reynanfc.cinelog.ui.theme.CineLogTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(
        uiState = uiState,
        onRetryClick = { viewModel.onEvent(HomeUiEvent.OnRetryClicked) },
        onRefresh = { viewModel.onEvent(HomeUiEvent.OnRefresh) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    uiState: HomeUiState,
    onRetryClick: () -> Unit,
    onRefresh: () -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CineLogTopBar(
                onMenuClick = { showBottomSheet = true }
            )
        },
        bottomBar = {
            CineLogBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }

            uiState.errorMessage != null -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = uiState.errorMessage,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge
                )
                Button(
                    onClick = onRetryClick,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Tentar novamente")
                }
            }

            else -> {
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = onRefresh,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp, vertical = 12.dp)
                            ) {
                                Text(
                                    text = "O que vamos assistir?",
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Descubra histórias para hoje",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        if (uiState.nowPlayingMovies.isNotEmpty()) {
                            item { MovieSection(title = "Em cartaz", movies = uiState.nowPlayingMovies) }
                        }

                        if (uiState.popularMovies.isNotEmpty()) {
                            item { MovieSection(title = "Populares", movies = uiState.popularMovies) }
                        }

                        if (uiState.nowPlayingMovies.isEmpty() && uiState.popularMovies.isEmpty()) {
                            item {
                                Text(
                                    text = "Nenhum filme disponível no momento.",
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showBottomSheet) {
        CineLogOptionsBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            onThemeClick = {},
            onAboutClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val movies = listOf(
        Movie(1, "Interestelar", "", null, null, "2014-11-05", 8.7),
        Movie(2, "Duna: Parte Dois", "", null, null, "2024-02-27", 8.5)
    )
    CineLogTheme {
        HomeContent(
            uiState = HomeUiState(
                nowPlayingMovies = movies,
                popularMovies = movies,
                isRefreshing = false
            ),
            onRetryClick = {},
            onRefresh = {}
        )
    }
}
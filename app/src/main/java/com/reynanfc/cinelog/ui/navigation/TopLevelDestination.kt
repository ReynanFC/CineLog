package com.reynanfc.cinelog.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

// Abas do bottom bar: cada uma é uma rota do NavHost
enum class TopLevelDestination(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    HOME(route = "home", label = "Início", icon = Icons.Default.Home),
    SEARCH(route = "search", label = "Buscar", icon = Icons.Default.Search),
    FAVORITES(route = "favorites", label = "Favoritos", icon = Icons.Default.Favorite)
}

package com.reynanfc.cinelog.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CineLogBottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit) {

    NavigationBar(
        containerColor = Color(0xFF1E1210),
        contentColor = Color.White
    ) {

        val itemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            selectedTextColor = Color.White,

            // Cor da pílula (indicador visual de fundo) do item SELECIONADO
            indicatorColor = Color(0xFF762126), // Vermelho (ex: estilo Netflix)

            // Cor do ícone e do texto quando NÃO SELECIONADO
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray        )

        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Início") },
            label = { Text("Início") },
            colors = itemColors
        )
        NavigationBarItem(
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            icon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            label = { Text("Buscar") },
            colors = itemColors
        )
        NavigationBarItem(
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favoritos") },
            label = { Text(text = "Favoritos") },
            colors = itemColors
        )
    }

}
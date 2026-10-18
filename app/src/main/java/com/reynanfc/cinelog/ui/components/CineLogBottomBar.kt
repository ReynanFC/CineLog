package com.reynanfc.cinelog.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.reynanfc.cinelog.ui.navigation.TopLevelDestination


@Composable
fun CineLogBottomBar(
    selectedDestination: TopLevelDestination?,
    onDestinationSelected: (TopLevelDestination) -> Unit
) {

    NavigationBar(
        containerColor = Color(0xFF1E1210),
        contentColor = Color.White
    ) {

        val itemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            selectedTextColor = Color.White,

            // Cor da pílula (indicador visual de fundo) do item SELECIONADO
            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.24f),

            // Cor do ícone e do texto quando NÃO SELECIONADO
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray
        )

        TopLevelDestination.entries.forEach { destination ->
            NavigationBarItem(
                selected = destination == selectedDestination,
                onClick = { onDestinationSelected(destination) },
                icon = { Icon(destination.icon, contentDescription = destination.label) },
                label = { Text(destination.label) },
                colors = itemColors
            )
        }
    }

}

@Preview
@Composable
private fun CineLogBottomBarPreview() {
    CineLogBottomBar(
        selectedDestination = TopLevelDestination.HOME,
        onDestinationSelected = {}
    )
}

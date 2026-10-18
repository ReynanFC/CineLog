package com.reynanfc.cinelog.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.reynanfc.cinelog.ui.components.CineLogBottomBar
import com.reynanfc.cinelog.ui.components.CineLogOptionsBottomSheet
import com.reynanfc.cinelog.ui.components.CineLogTopBar
import com.reynanfc.cinelog.ui.navigation.CineLogNavHost
import com.reynanfc.cinelog.ui.navigation.TopLevelDestination

@Composable
fun CineLogApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination = TopLevelDestination.entries.firstOrNull { destination ->
        backStackEntry?.destination?.hierarchy?.any { it.route == destination.route } == true
    }
    var showOptionsSheet by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CineLogTopBar(onMenuClick = { showOptionsSheet = true })
        },
        bottomBar = {
            CineLogBottomBar(
                selectedDestination = selectedDestination,
                onDestinationSelected = { destination ->
                    navController.navigateToTopLevel(destination)
                }
            )
        }
    ) { innerPadding ->
        CineLogNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }

    if (showOptionsSheet) {
        CineLogOptionsBottomSheet(
            onDismissRequest = { showOptionsSheet = false },
            onThemeClick = {},
            onAboutClick = {}
        )
    }
}

private fun NavHostController.navigateToTopLevel(destination: TopLevelDestination) {
    navigate(destination.route) {
        // Mantém só a aba inicial na pilha e preserva o estado de cada aba
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

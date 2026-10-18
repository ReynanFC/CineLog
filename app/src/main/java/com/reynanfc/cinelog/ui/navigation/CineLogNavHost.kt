package com.reynanfc.cinelog.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.reynanfc.cinelog.ui.favorites.FavoritesScreen
import com.reynanfc.cinelog.ui.home.HomeScreen
import com.reynanfc.cinelog.ui.search.SearchScreen

@Composable
fun CineLogNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = TopLevelDestination.HOME.route,
        modifier = modifier
    ) {
        composable(route = TopLevelDestination.HOME.route) {
            HomeScreen(
                onMovieClick = { movieId ->
                    navController.navigate("movie/$movieId")
                }
            )
        }

        composable(route = TopLevelDestination.SEARCH.route) {
            SearchScreen(
                onMovieClick = { movieId ->
                    navController.navigate("movie/$movieId")
                }
            )
        }

        composable(route = TopLevelDestination.FAVORITES.route) {
            FavoritesScreen()
        }

        composable(
            route = "movie/{movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments
                ?.getInt("movieId")
                ?: return@composable


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "ID do filme: $movieId")
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "Voltar")
                }
            }
        }
    }
}

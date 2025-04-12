package com.ynov.movieappv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ynov.movieappv2.tvshows.presentation.ui.popular.PopularShowsScreen
import com.ynov.movieappv2.tvshow_details.presentation.ui.details.ShowDetailsScreen
import com.ynov.movieappv2.ui.theme.MovieAppV2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppV2Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "popularShows") {
                        composable("popularShows") {
                            PopularShowsScreen(onShowClick = { permalink ->
                                navController.navigate("showDetails/$permalink")
                            })
                        }
                        composable("showDetails/{permalink}") { backStackEntry ->
                            val permalink = backStackEntry.arguments?.getString("permalink") ?: ""
                            ShowDetailsScreen(showPermalink = permalink)
                        }
                    }
                }
            }
        }
    }
}

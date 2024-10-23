package com.niyas.composeprofiler.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.niyas.composeprofiler.screen.ImageScreen
import com.niyas.composeprofiler.screen.MainScreen
import com.niyas.composeprofiler.screen.MoreProfiles
import com.niyas.composeprofiler.viewmodel.ProfileViewModel

@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: ProfileViewModel) {

    NavHost(navController = navController, startDestination = Screen.Main.route) {

        composable(Screen.Main.route) {
            MainScreen(navController, viewModel)
        }
        composable(Screen.ImageCarousel.route) { backStackEntry ->
            val profileId = backStackEntry.arguments?.getString("profileId") ?: ""
            ImageScreen(profileId, navController, viewModel)
        }
        composable(Screen.MoreProfiles.route) {
            MoreProfiles(navController, viewModel)
        }
    }
}




package com.niyas.composeprofiler.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.niyas.composeprofiler.navigation.Screen
import com.niyas.composeprofiler.viewmodel.ProfileViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .background(Color(0xFF33A4DC))
            .fillMaxSize()
    ) {
        MoreOptionsRow(
            text1 = "My Matches",
            text2 = "8 Profile pending with me",
            onMoreClick = {
                navController.navigate(Screen.MoreProfiles.route)
            }
        )
        ProfileList(viewModel = viewModel, navController)
    }
}

@Composable
fun ImageScreen(profileId: String, navController: NavController, viewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .background(Color(0xFFD6BD43))
            .fillMaxSize()
    ) {
        ImageCarousel(
            profileId = profileId,
            onBackClick = { navController.navigateUp() },
            viewModel = viewModel
        )
    }
}

@Composable
fun MoreProfiles(navController: NavController, viewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .background(Color(0xFF00C853))
            .fillMaxSize()
    ) {
        MoreUtilRow(
            title = "Today Recommendation",
            onMoreClick = {
                navController.navigateUp()
            }
        )
        MoreProfilesScreen(viewModel)

    }
}
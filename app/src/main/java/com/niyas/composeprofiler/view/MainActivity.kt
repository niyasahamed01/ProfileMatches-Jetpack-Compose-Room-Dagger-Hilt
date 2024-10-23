package com.niyas.composeprofiler.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.niyas.composeprofiler.screen.ImageCarousel
import com.niyas.composeprofiler.screen.MoreOptionsRow
import com.niyas.composeprofiler.screen.MoreProfilesScreen
import com.niyas.composeprofiler.screen.MoreUtilRow
import com.niyas.composeprofiler.screen.ProfileList
import com.niyas.composeprofiler.ui.theme.ComposeProfilerTheme
import com.niyas.composeprofiler.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ComposeProfilerTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            Column(
                                modifier = Modifier
                                    .background(Color(0xFF33A4DC)) // Use the correct color format
                                    .fillMaxSize()
                            ) {
                                MoreOptionsRow(
                                    text1 = "My Matches",
                                    text2 = "8 Profile pending with me",
                                    onMoreClick = {
                                        navController.navigate("more_profiles")
                                    })
                                ProfileList(viewModel = profileViewModel, navController)
                            }
                        }
                        composable("image/{profileId}") { backStackEntry ->
                            Column(
                                modifier = Modifier
                                    .background(Color(0xFFD6BD43))
                                    .fillMaxSize()
                            ) {
                                val profileId =
                                    backStackEntry.arguments?.getString("profileId") ?: ""
                                ImageCarousel(
                                    profileId = profileId,
                                    onBackClick = { navController.popBackStack() },
                                    viewModel = profileViewModel
                                )
                            }
                        }
                        composable("more_profiles") {
                            Column(
                                modifier = Modifier
                                    .background(Color(0xFF00C853))
                                    .fillMaxSize()
                            ) {
                                MoreUtilRow(
                                    title = "Today Recommendation",
                                    onMoreClick = {
                                        navController.popBackStack()
                                    })

                                MoreProfilesScreen(viewModel = profileViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}
package com.niyas.composeprofiler.navigation

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object ImageCarousel : Screen("image/{profileId}")
    data object MoreProfiles : Screen("more_profiles")
}
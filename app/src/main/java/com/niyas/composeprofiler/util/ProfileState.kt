package com.niyas.composeprofiler.util

data class ProfileState(
    val profiles: List<Profile> = emptyList(),
    val isLoading: Boolean = false
)
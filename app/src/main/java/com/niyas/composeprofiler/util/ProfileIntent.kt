package com.niyas.composeprofiler.util

sealed class ProfileIntent {
    data object Loading : ProfileIntent()
    data object Empty : ProfileIntent()
    data class Loaded(val profiles: List<Profile>) : ProfileIntent()
    data class Error(val message: String) : ProfileIntent()
}

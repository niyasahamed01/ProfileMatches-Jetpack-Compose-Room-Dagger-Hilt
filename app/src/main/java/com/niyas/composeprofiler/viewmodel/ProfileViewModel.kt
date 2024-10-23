package com.niyas.composeprofiler.viewmodel

import com.niyas.composeprofiler.R
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niyas.composeprofiler.util.ProfileState
import com.niyas.composeprofiler.room.ProfileDao
import com.niyas.composeprofiler.util.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileDao: ProfileDao,
    private val application: Application
) : ViewModel() {

    private val _profiles = MutableStateFlow(ProfileState())
    val profiles: StateFlow<ProfileState> = _profiles.asStateFlow()

    init {
        viewModelScope.launch {
            loadProfiles()
            insertStaticProfiles()
        }
    }

    private fun loadProfiles() {
        viewModelScope.launch {
            profileDao.getAllProfiles().collect { profiles ->
                _profiles.value = ProfileState(profiles = profiles)
            }
        }
    }

    fun getProfileById(id: String): Flow<Profile?> {
        return flow {
            val profile = _profiles.value.profiles.find { it.id == id.toInt() }
            emit(profile)
        }
    }

    fun deleteProfile(profile: Profile) {
        viewModelScope.launch {
            profileDao.deleteProfile(profile)
        }
    }

    // Insert static profiles into the Room database if empty
    suspend fun insertStaticProfiles() {
        if (profileDao.getAllProfiles().first().isEmpty()) {
            val staticProfiles = listOf(
                Profile(
                    id = 1,
                    name = "John Doe",
                    description = application.getString(R.string.profile_john_doe),
                    imageResId = R.drawable.actor,
                    idString = "M9837832"
                ),
                Profile(
                    id = 2,
                    name = "Jane Smith",
                    description = application.getString(R.string.profile_jane_smith),
                    imageResId = R.drawable.actor_two,
                    idString = "M9837833"
                ),
                Profile(
                    id = 3,
                    name = "Alice Johnson",
                    description = application.getString(R.string.profile_alice_johnson),
                    imageResId = R.drawable.actor_three,
                    idString = "M9837834"
                ),
                Profile(
                    id = 4,
                    name = "Bob Brown",
                    description = application.getString(R.string.profile_bob_brown),
                    imageResId = R.drawable.actor_four,
                    idString = "M9837835"
                ),
                Profile(
                    id = 5,
                    name = "Charlie Green",
                    description = application.getString(R.string.profile_charlie_green),
                    imageResId = R.drawable.actor_five,
                    idString = "M9837836"
                )
            )
            profileDao.insertProfile(staticProfiles)
        }
    }
}
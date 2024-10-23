package com.niyas.composeprofiler.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.niyas.composeprofiler.util.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profiles")
    fun getAllProfiles(): Flow<List<Profile>>

    @Insert
    suspend fun insertProfile(profile: List<Profile>)

    @Delete
    suspend fun deleteProfile(profile: Profile)
}

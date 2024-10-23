package com.niyas.composeprofiler.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niyas.composeprofiler.util.Profile


@Database(entities = [Profile::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}

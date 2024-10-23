package com.niyas.composeprofiler.di

import android.content.Context
import androidx.room.Room
import com.niyas.composeprofiler.room.AppDatabase
import com.niyas.composeprofiler.room.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "profile-database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideProfileDao(database: AppDatabase): ProfileDao {
        return database.profileDao()
    }
}
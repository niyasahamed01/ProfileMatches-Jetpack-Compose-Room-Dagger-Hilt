package com.niyas.composeprofiler.view

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) // Initialize Mobile Ads SDK
    }
}
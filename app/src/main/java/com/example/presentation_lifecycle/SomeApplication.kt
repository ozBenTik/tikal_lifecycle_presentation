package com.example.presentation_lifecycle

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SomeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
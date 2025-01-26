package com.example.noteapp06

import android.app.Application
import com.example.noteapp06.utils.PreferenceHelper

class App: Application() {
    
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
    }
}
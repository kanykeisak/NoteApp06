package com.example.noteapp06

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.noteapp06.model.data.db.AppDatabase
import com.example.noteapp06.utils.PreferenceHelper
import com.jakewharton.threetenabp.AndroidThreeTen

class App: Application() {
    
    companion object{
        var appDatabase: AppDatabase? = null
    }
    
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
        getInstance()
    }
    
    private fun getInstance(): AppDatabase? {
        if(appDatabase == null){
            appDatabase = applicationContext?.let{context ->
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}